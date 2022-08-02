package com.wqddg.apiordersubmit.feign;

import com.wqddg.apiordersubmit.feign.impl.ShopCatDelClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wqddg
 * @ClassName ShopCatDelClient
 * @DateTime: 2021/12/20 19:21
 * @remarks : #
 */
@FeignClient(value = "SHOPCART-DEL",fallback = ShopCatDelClientImpl.class)
public interface ShopCatDelClient {

    @DeleteMapping("/shopcart/del")
    int delect(@RequestParam("cids") String cids);


}
