package com.wqddg.orderclose.feign;

import com.wqddg.common.entity.ProductSku;
import com.wqddg.orderclose.feign.impl.StockUpdateClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockUpdateClient
 * @DateTime: 2021/12/20 23:35
 * @remarks : #
 */
@FeignClient(value = "STOCK-UPDATE",fallback = StockUpdateClientImpl.class)
public interface StockUpdateClient {
    @PostMapping("/stock/update")
    public int update(List<ProductSku> skus);
}
