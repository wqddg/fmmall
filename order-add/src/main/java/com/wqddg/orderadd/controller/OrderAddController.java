package com.wqddg.orderadd.controller;

import com.wqddg.entity.Orders;
import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.orderadd.service.OrderAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderAddController
 * @DateTime: 2021/12/19 22:35
 * @remarks : #
 */
@RestController
public class OrderAddController {
    @Autowired
    private OrderAddService orderAddService;


    @PostMapping("order/save")
    public List<ShoppingCartVo> saveOrder(@RequestBody Orders orders, String cids){
        return orderAddService.saveOrder(orders, cids);
    }
}
