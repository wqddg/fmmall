package com.wqddg.orderstatusupdate.service;

import com.wqddg.entity.Orders;


/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateService
 * @DateTime: 2021/12/20 22:15
 * @remarks : #
 */
public interface OrderStatusUpdateService {
    int ordersStatus(Orders orders);
}
