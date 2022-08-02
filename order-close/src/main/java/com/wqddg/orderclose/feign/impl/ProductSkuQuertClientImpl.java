package com.wqddg.orderclose.feign.impl;

import com.wqddg.common.entity.ProductSku;
import com.wqddg.orderclose.feign.ProductSkuQuertClient;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName ProductSkuQuertClientImpl
 * @DateTime: 2021/12/20 23:27
 * @remarks : #
 */
@Component
public class ProductSkuQuertClientImpl implements ProductSkuQuertClient {
    @Override
    public ProductSku queryProductSku(String skuId) {
        return null;
    }
}
