package com.wqddg.prodcctskuquery.service;

import com.wqddg.entity.ProductSku;

/**
 * @Author: wqddg
 * @ClassName ProductSkuService
 * @DateTime: 2021/12/20 23:19
 * @remarks : #
 */
public interface ProductSkuService {

    ProductSku queryProductSku(String productSkuId);
}
