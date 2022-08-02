package com.wqddg.ordertimeoutcancle.feign;

import com.wqddg.ordertimeoutcancle.feign.impl.OrderClostClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wqddg
 * @ClassName OrderClostClient
 * @DateTime: 2021/12/21 16:39
 * @remarks : #
 */
@FeignClient(value = "ORDER-CLOSE",fallback = OrderClostClientImpl.class)
public interface OrderClostClient {
    @GetMapping("order/close")
    public int close(@RequestParam("orderId") String orderId,@RequestParam("closeType") int closeType);
}
