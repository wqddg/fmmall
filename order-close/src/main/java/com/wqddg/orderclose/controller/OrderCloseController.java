package com.wqddg.orderclose.controller;

import com.wqddg.orderclose.service.OrderCloseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName OrderCloseController
 * @DateTime: 2021/12/20 22:22
 * @remarks : #
 */
@RestController
public class OrderCloseController {
    @Autowired
    private OrderCloseService orderCloseService;
    @GetMapping("order/close")
    public int close(String orderId,int closeType){
        return orderCloseService.closeService(orderId,closeType);
    }
}
