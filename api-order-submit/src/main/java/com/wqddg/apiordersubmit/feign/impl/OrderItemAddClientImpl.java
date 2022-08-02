package com.wqddg.apiordersubmit.feign.impl;

import com.wqddg.apiordersubmit.feign.OrderItemAddClient;
import com.wqddg.common.entity.ShoppingCartVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemAddClientImpl
 * @DateTime: 2021/12/20 15:08
 * @remarks : #
 */
@Component
public class OrderItemAddClientImpl implements OrderItemAddClient {
    @Override
    public int addorderItme(List<ShoppingCartVo> cartVos, String orderId) {
        return 0;
    }
}
