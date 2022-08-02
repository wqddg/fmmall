package com.wqddg.orderadd.service;

import com.wqddg.entity.Orders;
import com.wqddg.entity.ShoppingCartVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderAddService
 * @DateTime: 2021/12/19 22:36
 * @remarks : #
 */
public interface OrderAddService {
    public List<ShoppingCartVo> saveOrder(@RequestBody Orders orders, String cids);
}
