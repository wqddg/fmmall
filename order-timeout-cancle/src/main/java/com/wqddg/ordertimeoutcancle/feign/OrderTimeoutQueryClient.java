package com.wqddg.ordertimeoutcancle.feign;

import com.wqddg.common.entity.Orders;
import com.wqddg.ordertimeoutcancle.feign.impl.OrderTimeoutQueryClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderTimeoutQueryClient
 * @DateTime: 2021/12/21 16:19
 * @remarks : #
 */
@FeignClient(value = "ORDER-TIMEOUT-QUERY",fallback = OrderTimeoutQueryClientImpl.class)
public interface OrderTimeoutQueryClient {

    @GetMapping("order/query/timeout")
    public List<Orders> query();
}
