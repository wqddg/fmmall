package com.wqddg.apiordersubmit.feign;

import com.wqddg.common.entity.Orders;
import com.wqddg.common.entity.ShoppingCartVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.wqddg.apiordersubmit.feign.impl.OrderAddClientImpl;

/**
 * @Author: wqddg
 * @ClassName OrderAddClient
 * @DateTime: 2021/12/20 13:40
 * @remarks : #
 */
@FeignClient(value = "ORDER-ADD",fallback = OrderAddClientImpl.class)
public interface OrderAddClient {

    @PostMapping("order/save")
    public List<ShoppingCartVo> saveOrder(Orders orders,@RequestParam("cids") String cids);
}
