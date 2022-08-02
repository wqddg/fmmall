package com.wqddg.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.wqddg.common.vo.PageHelper;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.OrderItemMapper;
import com.wqddg.dao.OrdersMapper;
import com.wqddg.dao.ProductSkuMapper;
import com.wqddg.dao.ShoppingCartMapper;
import com.wqddg.entity.*;
import com.wqddg.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.annotation.Order;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName OrderServiceImpl
 * @DateTime: 2021/11/22 23:43
 * @remarks : #
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 保存订单的步骤  同时成功和同时失败
     * @param cids
     * @param orders
     * @return
     */
    @Transactional
    @Override
    public ResultVo addOrder(String cids, Orders orders) {
        Map<String,String> maps=new HashMap<>();
        //1. 根据cids查询当前订单中关联的购物车记录详情(包括库存)
        String[] split = cids.split(",");
        List<Integer> integers=new ArrayList<>();
        for (String s : split) {
            integers.add(Integer.parseInt(s));
        }
        //根据用户在购物车列表中选择的购物车记录的id 查询到对应的购物车记录
        List<ShoppingCartVo> shoppingCartVos = shoppingCartMapper.selectShopcartByyCids_Sku(integers);
        //从购物车信息中获取到要购买的SKUID(商品ID) 以SKUID为key写到redis中:
        boolean isLock=true;
        String[] strings=new String[shoppingCartVos.size()];
        Map<String,RLock> rLockMap=new HashMap<>();
        for (int i = 0; i < shoppingCartVos.size(); i++) {
            String skuId = shoppingCartVos.get(i).getSkuId();
            boolean b= false;
            try {
                //构建商品的锁
                RLock lock = redissonClient.getLock(skuId);
                b = lock.tryLock(10, 3,TimeUnit.SECONDS);
                if (b){
                    strings[i]=skuId;
                    rLockMap.put(skuId,lock);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isLock=isLock&b;
        }
        //如果isLock为true 表示加锁成功
        if (isLock){
            //2.校验库存
            boolean isF=true;
            String untitled="";
            for (int i=0;i<shoppingCartVos.size();i++) {
                if (Integer.parseInt( shoppingCartVos.get(i).getCartNum())>shoppingCartVos.get(i).getSkuStock()){
                    isF=false;
                }
                //获取所有的商品名称   拼接成为字符串
                if (i==shoppingCartVos.size()-1){
                    untitled=untitled+shoppingCartVos.get(i).getProductName();
                }else {
                    untitled=untitled+shoppingCartVos.get(i).getProductName()+",";
                }
            }
            if (isF){
                //保存库存足够
                //a.userId
                //b.项目名称
                //c.收获信息为主
                //d.总价格
                //e.支付方式
                //f.订单创建时间
                //g:订单初始状态
                orders.setUntitled(untitled);
                orders.setStatus("1");
                orders.setCreateTime(new Date());
                //生产订单编号
                String uuid = UUID.randomUUID().toString().replace("-", "");
                orders.setOrderId(uuid);
                int insert = ordersMapper.insert(orders);
                if (insert>0){
                    for (int i=0;i<shoppingCartVos.size();i++) {
                        String itemId=System.currentTimeMillis()+""+(new Random().nextInt(89999)+10000);
                        OrderItem orderItem = new OrderItem(itemId, uuid, shoppingCartVos.get(i).getProductId(), shoppingCartVos.get(i).getProductName(),
                                shoppingCartVos.get(i).getProductImg(), shoppingCartVos.get(i).getSkuId(), shoppingCartVos.get(i).getSkuName(), new BigDecimal(shoppingCartVos.get(i).getSellPrice()),
                                Integer.parseInt(shoppingCartVos.get(i).getCartNum()), new BigDecimal(shoppingCartVos.get(i).getSellPrice() * Integer.parseInt(shoppingCartVos.get(i).getCartNum())), new Date(), new Date(), 0);
                        orderItemMapper.insert(orderItem);
                    }
                    //5.扣减库存    更加套餐ID修改套餐库存量
                    // 我们下单了多少  就减少多少
                    for (ShoppingCartVo cartVo : shoppingCartVos) {
                        String skuId = cartVo.getSkuId();
                        int newStock=cartVo.getSkuStock()-Integer.parseInt(cartVo.getCartNum());
                        ProductSku productSku=new ProductSku();
                        productSku.setSkuId(skuId);
                        productSku.setStock(newStock);
                        int updateByExample = productSkuMapper.updateByPrimaryKeySelective(productSku);
                        if (updateByExample<0){
                            return new ResultVo(ResStatus.NO,"系统异常请重试",null);
                        }
                    }
                    //5.删除购物车
                    for (int cartVo : integers) {
                        shoppingCartMapper.deleteByPrimaryKey(cartVo);
                    }
                    //释放锁
                    for (int i = 0; i < strings.length; i++) {
                        String skuId=strings[i];
                        if (skuId!=null && !"".equals(skuId)){
                            //释放锁
                            rLockMap.get(skuId).unlock();
                        }
                    }
                    maps.put("order_id",uuid);
                    maps.put("product_names",untitled);
                    return new ResultVo(ResStatus.OK,"购买成功",maps);

                }else {
                    return new ResultVo(ResStatus.NO,"系统异常请重试",null);
                }
            }else {
                //库存不足
                return new ResultVo(ResStatus.NO,"库存不足  请重新选择",null);
            }
        }else {
            //表示加锁失败、订单添加失败
            //当加锁失败时、有可能对部分商品已经锁定、要释放锁定的部分商品
            for (int i = 0; i < strings.length; i++) {
                String skuId=strings[i];
                if (skuId!=null && !"".equals(skuId)){
                    //释放锁
                    rLockMap.get(skuId).unlock();
                }
            }
            return new ResultVo(ResStatus.NO,"系统异常请重试",null);
        }
    }

    @Override
    public int UpdateOrderStatus(String orderId, String statics) {
        Orders orders=new Orders();
        orders.setStatus(statics);
        orders.setOrderId(orderId);
        int orders_i = ordersMapper.updateByPrimaryKeySelective(orders);
        return orders_i;
    }

    @Override
    public ResultVo getOrderByid(String orderId) {
        final Orders orders = ordersMapper.selectByPrimaryKey(orderId);
        return new ResultVo(ResStatus.OK,"success",orders.getStatus());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)//事务的级别  或者加锁
    public void closeOrder(String orderId) {
        synchronized (this){
            //a.修改当前订单:status=6  已关闭  close_type=1 超时未支付
            Orders orders_update=new Orders();
            orders_update.setOrderId(orderId);
            orders_update.setStatus("6");
            orders_update.setCloseType(1);
            ordersMapper.updateByPrimaryKeySelective(orders_update);
            //b.还原库存:先根据当前订单编号查询商品快照(Skuid buy_count)----->修改product_sku
            Example example_OrderItem=new Example(OrderItem.class);
            Example.Criteria criteria_OrderItem = example_OrderItem.createCriteria();
            criteria_OrderItem.andEqualTo("orderId",orderId);
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example_OrderItem);
            for (OrderItem orderItem : orderItems) {
                //修改
                ProductSku productSku = productSkuMapper.selectByPrimaryKey(orderItem.getSkuId());
                productSku.setStock(productSku.getStock()+orderItem.getBuyCounts());
                productSkuMapper.updateByPrimaryKey(productSku);
            }
        }
    }

    @Override
    public ResultVo listOrders(String userId, String status, int pageNum, int limit) {
        //分页查询数据
        int start=(pageNum-1)*limit;
        List<OrdersVo> ordersVos = ordersMapper.selectOrders(userId, status, start, limit);
        //查询总记录数
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userId",userId);
        if (status!=null&&!status.equals("")){
        criteria.andLike("status",status);
        }
        //分页的总数
        int count = ordersMapper.selectCountByExample(example);
        //计算总页数
        int pageCount=count%limit==0?count/limit:count/limit+1;
        return new ResultVo(ResStatus.OK,"success",new PageHelper<OrdersVo>(count,pageCount,ordersVos));
    }
}
