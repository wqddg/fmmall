package com.wqddg.orderclose.feign.impl;

import com.wqddg.common.entity.OrderItem;
import com.wqddg.orderclose.feign.OrderItemQueryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemQueryClientImpl
 * @DateTime: 2021/12/20 22:56
 * @remarks : #
 */
@Component
public class OrderItemQueryClientImpl implements OrderItemQueryClient {
    @Override
    public List<OrderItem> query(String orderId) {
        return null;
    }
}
