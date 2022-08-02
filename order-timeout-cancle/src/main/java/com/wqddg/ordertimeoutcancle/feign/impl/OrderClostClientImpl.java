package com.wqddg.ordertimeoutcancle.feign.impl;

import com.wqddg.ordertimeoutcancle.feign.OrderClostClient;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName OrderClostClientImpl
 * @DateTime: 2021/12/21 16:39
 * @remarks : #
 */
@Component
public class OrderClostClientImpl implements OrderClostClient {
    @Override
    public int close(String orderId, int closeType) {
        return 0;
    }
}
