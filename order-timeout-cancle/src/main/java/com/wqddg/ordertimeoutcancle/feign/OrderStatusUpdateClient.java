package com.wqddg.ordertimeoutcancle.feign;

import com.wqddg.common.entity.Orders;
import com.wqddg.ordertimeoutcancle.feign.impl.OrderStatusUpdateClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateClient
 * @DateTime: 2021/12/21 16:35
 * @remarks : #
 */
@FeignClient(value = "ORDER-STATUS-UPDATE",fallback = OrderStatusUpdateClientImpl.class)
public interface OrderStatusUpdateClient {

    @PutMapping("order/status/upadte")
    public int update(Orders orders);
}
