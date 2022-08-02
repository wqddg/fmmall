package com.wqddg.apiuserlogin.fiegn.impl;

import com.wqddg.apiuserlogin.fiegn.UserCheck;
import com.wqddg.common.entity.Users;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName UserCheckImpl
 * @DateTime: 2021/12/18 17:58
 * @remarks : #
 */
@Component
public class UserCheckImpl implements UserCheck {
    @Override
    public Users check(String username) {
        System.out.println("服务降级");
        return null;
    }
}
