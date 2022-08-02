package com.wqddg.orderitemadd.controller;

import com.wqddg.entity.OrderItem;
import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.orderitemadd.service.OrderItemAddService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: wqddg
 * @ClassName OrderItemAddController
 * @DateTime: 2021/12/19 21:15
 * @remarks : #
 */
@RestController
public class OrderItemAddController {

    @Autowired
    private OrderItemAddService orderItemAddService;

    @PostMapping("item/save")
    public int addorderItme(@RequestBody List<ShoppingCartVo> cartVos, String orderId){
        return orderItemAddService.save(cartVos, orderId);
    }
}
