package com.wqddg.orderitemquery.controller;

import com.wqddg.entity.OrderItem;
import com.wqddg.orderitemquery.service.OrderItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemQueryController
 * @DateTime: 2021/12/20 22:36
 * @remarks : #
 */
@RestController
public class OrderItemQueryController {
    @Autowired
    private OrderItemQueryService orderItemQueryService;


    @GetMapping("orderitem/query")
    public List<OrderItem> query(String orderId){
        return   orderItemQueryService.query(orderId);
    }
}
