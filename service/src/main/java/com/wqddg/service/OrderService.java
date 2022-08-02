package com.wqddg.service;

import com.wqddg.common.vo.ResultVo;
import com.wqddg.entity.Orders;

/**
 * @Author: wqddg
 * @ClassName OrderService
 * @DateTime: 2021/11/22 23:42
 * @remarks : #订单
 */
public interface OrderService {
    ResultVo addOrder(String cids, Orders orders);

    int UpdateOrderStatus(String orderId,String statics);


    ResultVo getOrderByid(String orderId);


    void closeOrder(String orderId);


    ResultVo listOrders(String userId,String status,int pageNum,int limit);
}
