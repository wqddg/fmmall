package com.wqddg.orderstatusupdate.controller;

import com.wqddg.entity.Orders;
import com.wqddg.orderstatusupdate.service.OrderStatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateController
 * @DateTime: 2021/12/20 22:14
 * @remarks : #
 */
@RestController
public class OrderStatusUpdateController {
    @Autowired
    private OrderStatusUpdateService orderStatusUpdateService;
    @PutMapping("order/status/upadte")
    public int update(@RequestBody Orders orders){
        return orderStatusUpdateService.ordersStatus(orders);
    }

}
