package com.wqddg.orderclose.feign;

import com.wqddg.common.entity.Orders;
import com.wqddg.orderclose.feign.impl.OrderStatusUpdateClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateClient
 * @DateTime: 2021/12/20 22:50
 * @remarks : #
 */
@FeignClient(value = "ORDER-STATUS-UPDATE",fallback = OrderStatusUpdateClientImpl.class)
public interface OrderStatusUpdateClient {
    @PutMapping("order/status/upadte")
    public int update(Orders orders);
}
