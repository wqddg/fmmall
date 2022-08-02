package com.wqddg.apiordersubmit.feign.impl;

import com.wqddg.apiordersubmit.feign.StockUpdateClient;
import com.wqddg.common.entity.ProductSku;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockUpdateClientImpl
 * @DateTime: 2021/12/20 15:26
 * @remarks : #
 */
@Component
public class StockUpdateClientImpl implements StockUpdateClient {
    @Override
    public int update(List<ProductSku> skus) {
        return 0;
    }
}
