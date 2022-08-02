package com.wqddg.apiuserlogin.service;

import com.wqddg.common.vo.ResultVo;

/**
 * @Author: wqddg
 * @ClassName UserLoginService
 * @DateTime: 2021/12/18 17:03
 * @remarks : #
 */

public interface UserLoginService {
    //用户登录
    ResultVo checkLogin(String username,String password);
}
