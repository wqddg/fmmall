package com.wqddg.ordertimeoutcancle.feign.impl;

import com.wqddg.common.entity.Orders;
import com.wqddg.ordertimeoutcancle.feign.OrderTimeoutQueryClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderTimeoutQueryClientImpl
 * @DateTime: 2021/12/21 16:21
 * @remarks : #
 */
@Component
public class OrderTimeoutQueryClientImpl implements OrderTimeoutQueryClient {
    @Override
    public List<Orders> query() {
        return new ArrayList<>();
    }
}
