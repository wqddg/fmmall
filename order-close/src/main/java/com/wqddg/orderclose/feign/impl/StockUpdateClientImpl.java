package com.wqddg.orderclose.feign.impl;

import com.wqddg.common.entity.ProductSku;
import com.wqddg.orderclose.feign.StockUpdateClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockUpdateClientImpl
 * @DateTime: 2021/12/20 23:35
 * @remarks : #
 */
@Component
public class StockUpdateClientImpl implements StockUpdateClient {
    @Override
    public int update(List<ProductSku> skus) {
        return 0;
    }
}
