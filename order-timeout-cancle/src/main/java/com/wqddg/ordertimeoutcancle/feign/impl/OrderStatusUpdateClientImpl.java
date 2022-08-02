package com.wqddg.ordertimeoutcancle.feign.impl;

import com.wqddg.common.entity.Orders;
import com.wqddg.ordertimeoutcancle.feign.OrderStatusUpdateClient;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateClientImpl
 * @DateTime: 2021/12/21 16:35
 * @remarks : #
 */
@Component
public class OrderStatusUpdateClientImpl implements OrderStatusUpdateClient {
    @Override
    public int update(Orders orders) {
        return 0;
    }
}
