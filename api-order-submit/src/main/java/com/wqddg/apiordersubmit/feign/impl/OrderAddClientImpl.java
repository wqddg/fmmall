package com.wqddg.apiordersubmit.feign.impl;

import com.wqddg.apiordersubmit.feign.OrderAddClient;
import com.wqddg.common.entity.Orders;
import com.wqddg.common.entity.ShoppingCartVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderAddClientImpl
 * @DateTime: 2021/12/20 13:43
 * @remarks : #
 */
@Component
public class OrderAddClientImpl implements OrderAddClient {
    @Override
    public List<ShoppingCartVo> saveOrder(Orders orders, String cids) {
        return null;
    }
}
