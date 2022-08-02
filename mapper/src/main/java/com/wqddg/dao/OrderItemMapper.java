package com.wqddg.dao;

import com.wqddg.entity.OrderItem;
import com.wqddg.general.GeneralDAO;

import java.util.List;

public interface OrderItemMapper extends GeneralDAO<OrderItem> {

    public List<OrderItem> listOrderItemsByOrderId(String orderId);
}