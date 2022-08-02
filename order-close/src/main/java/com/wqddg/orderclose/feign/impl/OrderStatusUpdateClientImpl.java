package com.wqddg.orderclose.feign.impl;

import com.wqddg.common.entity.Orders;
import com.wqddg.orderclose.feign.OrderStatusUpdateClient;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateClientImpl
 * @DateTime: 2021/12/20 22:50
 * @remarks : #
 */
@Component
public class OrderStatusUpdateClientImpl implements OrderStatusUpdateClient {
    @Override
    public int update(Orders orders) {
        return 0;
    }
}
