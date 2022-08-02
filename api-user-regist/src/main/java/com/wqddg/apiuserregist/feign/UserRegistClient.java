package com.wqddg.apiuserregist.feign;

import com.wqddg.apiuserregist.feign.impl.UserRegistClientImpl;
import com.wqddg.common.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: wqddg
 * @ClassName UserRegistClient
 * @DateTime: 2021/12/19 15:31
 * @remarks : #
 */
@FeignClient(value = "USER-REGIST",fallback = UserRegistClientImpl.class)
public interface UserRegistClient {

    @PostMapping("user/save")
    int regis(Users users);
}
