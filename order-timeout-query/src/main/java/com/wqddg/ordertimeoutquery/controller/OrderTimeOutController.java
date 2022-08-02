package com.wqddg.ordertimeoutquery.controller;


import com.wqddg.entity.Orders;
import com.wqddg.ordertimeoutquery.service.OrderTimeOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderTimeOutController
 * @DateTime: 2021/12/20 22:04
 * @remarks : #
 */
@RestController
public class OrderTimeOutController {
    @Autowired
    private OrderTimeOutService outService;


    @GetMapping("order/query/timeout")
    public List<Orders> query(){
        //查询超时并未支付的订单
        return outService.query();
    }
}
