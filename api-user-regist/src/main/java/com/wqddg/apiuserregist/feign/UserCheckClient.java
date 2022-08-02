package com.wqddg.apiuserregist.feign;

import com.wqddg.apiuserregist.feign.impl.UserCheckClientImpl;
import com.wqddg.common.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wqddg
 * @ClassName UserCheckClient
 * @DateTime: 2021/12/19 15:26
 * @remarks : #
 */
@FeignClient(value = "USER-CHECK",fallback = UserCheckClientImpl.class)
public interface UserCheckClient {

    @GetMapping("user/check")
    Users check(@RequestParam("username") String username);
}
