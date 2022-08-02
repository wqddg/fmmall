package com.wqddg.apiuserlogin.fiegn;

import com.wqddg.apiuserlogin.fiegn.impl.UserCheckImpl;
import com.wqddg.common.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wqddg
 * @ClassName UserCheck
 * @DateTime: 2021/12/18 17:55
 * @remarks : #
 */
@FeignClient(value = "USER-CHECK",fallback  = UserCheckImpl.class)
public interface UserCheck {

    @GetMapping("user/check")
    Users check(@RequestParam("username") String username);

}
