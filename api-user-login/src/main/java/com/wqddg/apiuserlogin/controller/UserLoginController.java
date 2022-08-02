package com.wqddg.apiuserlogin.controller;

import com.wqddg.apiuserlogin.service.UserLoginService;
import com.wqddg.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName UserLoginController
 * @DateTime: 2021/12/18 16:30
 * @remarks : #
 */
@RestController
@CrossOrigin
public class UserLoginController {
    @Autowired
    public UserLoginService userLoginService;

    @GetMapping("user/login")
    public ResultVo login(String username, String password){
        return userLoginService.checkLogin(username,password);
    }


}
