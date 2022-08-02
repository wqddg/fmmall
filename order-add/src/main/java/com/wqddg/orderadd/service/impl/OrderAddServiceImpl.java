package com.wqddg.orderadd.service.impl;

import com.wqddg.entity.Orders;
import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.orderadd.dao.OrderMapper;
import com.wqddg.orderadd.feign.StockQueryClient;
import com.wqddg.orderadd.service.OrderAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wqddg
 * @ClassName OrderAddServiceImpl
 * @DateTime: 2021/12/19 22:37
 * @remarks : #
 */
@Service
public class OrderAddServiceImpl implements OrderAddService {
    @Autowired
    private StockQueryClient stockQueryClient;
    @Autowired
    private OrderMapper ordersMapper;

    @Override
    public List<ShoppingCartVo> saveOrder(Orders orders, String cids) {
        //1.校验库存
        List<ShoppingCartVo> shoppingCartVos = stockQueryClient.query(cids);
        if (shoppingCartVos!=null||shoppingCartVos.size()>0){
            //判断库存
            boolean isF=true;
            String untitled="";
            for (int i=0;i<shoppingCartVos.size();i++) {
                if (Integer.parseInt( shoppingCartVos.get(i).getCartNum())>shoppingCartVos.get(i).getSkuStock()){
                    isF=false;
                    break;
                }
                //获取所有的商品名称   拼接成为字符串
                if (i==shoppingCartVos.size()-1){
                    untitled=untitled+shoppingCartVos.get(i).getProductName();
                }else {
                    untitled=untitled+shoppingCartVos.get(i).getProductName()+",";
                }
            }
            //a.调用stock-query服务查询当前订单中保护的购物车消息
            if (isF){
                orders.setUntitled(untitled);
                orders.setStatus("1");
                orders.setCreateTime(new Date());
                //生产订单编号
//                String uuid = UUID.randomUUID().toString().replace("-", "");
//                orders.setOrderId(uuid);
                int insert = ordersMapper.insert(orders);
                if (insert>0){
                    return shoppingCartVos;
                }
            }
        }
        return null;
    }
}
