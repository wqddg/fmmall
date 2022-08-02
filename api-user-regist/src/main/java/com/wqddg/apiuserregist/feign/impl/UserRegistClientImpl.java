package com.wqddg.apiuserregist.feign.impl;

import com.wqddg.apiuserregist.feign.UserRegistClient;
import com.wqddg.common.entity.Users;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName UserRegistClientImpl
 * @DateTime: 2021/12/19 15:32
 * @remarks : #
 */
@Component
public class UserRegistClientImpl implements UserRegistClient {
    @Override
    public int regis(Users users) {
        return -1;
    }
}
