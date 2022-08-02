package com.wqddg.orderadd.feign;

import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.orderadd.feign.impl.StockQueryClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockQueryClient
 * @DateTime: 2021/12/19 23:06
 * @remarks : #
 */
@FeignClient(value = "STOCK-QUERY",fallback = StockQueryClientImpl.class)
public interface StockQueryClient {

    @GetMapping("stock/query")
    public List<ShoppingCartVo> query(@RequestParam("cids") String cids);
}
