package com.wqddg.orderitemadd.service;

import com.wqddg.entity.ShoppingCartVo;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemAddService
 * @DateTime: 2021/12/19 21:19
 * @remarks : #
 */
public interface OrderItemAddService {
    int save(List<ShoppingCartVo> cartVos, String orderId);
}
