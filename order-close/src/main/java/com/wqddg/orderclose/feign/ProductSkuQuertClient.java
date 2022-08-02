package com.wqddg.orderclose.feign;

import com.wqddg.common.entity.ProductSku;
import com.wqddg.orderclose.feign.impl.ProductSkuQuertClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wqddg
 * @ClassName ProductSkuQuertClient
 * @DateTime: 2021/12/20 23:27
 * @remarks : #
 */
@FeignClient(value = "PRODCCT-SKU-QUERY",fallback = ProductSkuQuertClientImpl.class)
public interface ProductSkuQuertClient {
    @GetMapping("product/sku/query")
    public ProductSku queryProductSku(@RequestParam("skuId") String skuId);
}
