package com.wqddg.orderclose.feign;

import com.wqddg.common.entity.OrderItem;
import com.wqddg.orderclose.feign.impl.OrderItemQueryClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemQueryClient
 * @DateTime: 2021/12/20 22:56
 * @remarks : #
 */
@FeignClient(value = "ORDERITEM-QUERY",fallback = OrderItemQueryClientImpl.class)
public interface OrderItemQueryClient {
    @GetMapping("orderitem/query")
    public List<OrderItem> query(@RequestParam("orderId") String orderId);
}
