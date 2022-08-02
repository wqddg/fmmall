package com.wqddg.ordertimeoutcancle.service;

import com.github.wxpay.sdk.WXPay;
import com.wqddg.common.entity.Orders;
import com.wqddg.ordertimeoutcancle.config.MyPayConfig;
import com.wqddg.ordertimeoutcancle.feign.OrderClostClient;
import com.wqddg.ordertimeoutcancle.feign.OrderStatusUpdateClient;
import com.wqddg.ordertimeoutcancle.feign.OrderTimeoutQueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName OrderTimoutCancleJob
 * @DateTime: 2021/12/20 23:43
 * @remarks : #
 */
@Component
public class OrderTimoutCancleJob {


    @Autowired
    private OrderTimeoutQueryClient orderTimeoutQueryClient;
    @Autowired
    private OrderStatusUpdateClient orderStatusUpdateClient;
    @Autowired
    private OrderClostClient orderClostClient;

    private WXPay wxPay=new WXPay(new MyPayConfig());
    @Scheduled(cron = "0/3 * * * * ?")
    public void cancleOrder(){
        //查询超时订单
        try {
            //使用  ORDER-TIMEOUT-QUERY
            List<Orders> orders = orderTimeoutQueryClient.query();
            for (Orders order : orders) {
                //访问微信平台接口、确认当前订单的最终支付状态
                //如果已支付  则修改订单状态为代发货、已支付等
                Map<String,String> params=new HashMap<>();
                params.put("out_trade_no",order.getOrderId());
                Map<String, String> map = wxPay.orderQuery(params);
                System.out.println(map.toString());
                if ("SUCCESS".equalsIgnoreCase(map.get("trade_state"))){//支付成功  修改我们的订单为已支付
                    Orders orders_update=new Orders();
                    orders_update.setOrderId(order.getOrderId());
                    orders_update.setStatus("2");
                    //ORDER-STATUS-UPDATE
                    int update = orderStatusUpdateClient.update(orders_update);
                    System.out.println(update);
                }else {//未支付  因为我大部分时间
                    //未支付我们开始取消订单  我们先关闭此订单 然后再进行数据库交互
                    Map<String, String> map1 = wxPay.closeOrder(params);
                    //ORDER-CLOSE
                    int close = orderClostClient.close(order.getOrderId(), 1);
                    //关闭订单
                    System.out.println(close);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
