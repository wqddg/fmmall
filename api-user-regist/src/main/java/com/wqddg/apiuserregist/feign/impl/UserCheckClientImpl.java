package com.wqddg.apiuserregist.feign.impl;

import com.wqddg.apiuserregist.feign.UserCheckClient;
import com.wqddg.common.entity.Users;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName UserCheckClientImpl
 * @DateTime: 2021/12/19 15:27
 * @remarks : #
 */
@Component
public class UserCheckClientImpl implements UserCheckClient {
    @Override
    public Users check(String username) {
        return new Users();
    }
}
