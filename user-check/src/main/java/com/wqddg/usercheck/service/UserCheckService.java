package com.wqddg.usercheck.service;

import com.wqddg.entity.Users;

/**
 * @Author: wqddg
 * @ClassName UserCheckService
 * @DateTime: 2021/12/18 17:38
 * @remarks : #
 */
public interface UserCheckService {


    Users checkLogin(String username);
}
