package com.wqddg.apiordersubmit.feign;

import com.wqddg.apiordersubmit.feign.impl.OrderItemAddClientImpl;
import com.wqddg.common.entity.ShoppingCartVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemAddClient
 * @DateTime: 2021/12/20 15:07
 * @remarks : #
 */
@FeignClient(value = "ORDERITEM-ADD",fallback = OrderItemAddClientImpl.class)
public interface OrderItemAddClient {
    @PostMapping("item/save")
    public int addorderItme(List<ShoppingCartVo> cartVos,@RequestParam("orderId") String orderId);
}
