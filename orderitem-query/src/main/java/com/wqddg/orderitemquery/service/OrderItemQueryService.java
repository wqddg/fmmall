package com.wqddg.orderitemquery.service;

import com.wqddg.entity.OrderItem;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemQueryService
 * @DateTime: 2021/12/20 22:40
 * @remarks : #
 */
public interface OrderItemQueryService {

    public List<OrderItem> query(String orderId);
}
