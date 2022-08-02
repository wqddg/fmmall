package com.wqddg.service.timeJob;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.wqddg.dao.OrderItemMapper;
import com.wqddg.dao.OrdersMapper;
import com.wqddg.dao.ProductSkuMapper;
import com.wqddg.entity.OrderItem;
import com.wqddg.entity.Orders;
import com.wqddg.entity.ProductSku;
import com.wqddg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName Print
 * @DateTime: 2021/11/24 21:03
 * @remarks : #我们的定时任务
 */
@Component
public class Print {


    private WXPay wxPay1=new WXPay(new MyPayConfig());
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0/60 * * * * ?")
    public void checkAndCloseOrder(){
        try {
            //查询超过30min订单状态依然为待支付状态的订单
            Example example_Orders=new Example(Orders.class);
            Example.Criteria criteria_Orders = example_Orders.createCriteria();
            criteria_Orders.andEqualTo("status","1");
            //现在前面的三十分钟
            Date time=new Date(System.currentTimeMillis()-30*60*1000);
            //这个是进行比较
            criteria_Orders.andLessThan("createTime",time);
            //这些都是过期的
            List<Orders> orders = ordersMapper.selectByExample(example_Orders);
            for (Orders order : orders) {
                //访问微信平台接口、确认当前订单的最终支付状态
                //如果已支付  则修改订单状态为代发货、已支付等
                Map<String,String> params=new HashMap<>();
                params.put("out_trade_no",order.getOrderId());
                Map<String, String> map = wxPay1.orderQuery(params);
                System.out.println(map.toString());
                if ("SUCCESS".equalsIgnoreCase(map.get("trade_state"))){//支付成功  修改我们的订单为已支付
                    Orders orders_update=new Orders();
                    orders_update.setOrderId(order.getOrderId());
                    orders_update.setStatus("2");
                    ordersMapper.updateByPrimaryKeySelective(orders_update);
                }else {//未支付  因为我大部分时间
                    //未支付我们开始取消订单  我们先关闭此订单 然后再进行数据库交互
                    Map<String, String> map1 = wxPay1.closeOrder(params);
                    orderService.closeOrder(order.getOrderId());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
