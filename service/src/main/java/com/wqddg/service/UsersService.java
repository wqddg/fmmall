package com.wqddg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wqddg.common.vo.ResultVo;

/**
 * @Author: wqddg
 * @ClassName UsersService
 * @DateTime: 2021/11/13 23:00
 * @remarks : #
 */
public interface UsersService {
    //用户注册
    ResultVo usersResgit(String username,String password);
    //用户登录
    ResultVo checkLogin(String username,String password);

}
