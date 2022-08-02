package com.wqddg.usercheck.controller;

import com.wqddg.entity.Users;
import com.wqddg.usercheck.service.UserCheckService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;

/**
 * @Author: wqddg
 * @ClassName UserCheckController
 * @DateTime: 2021/12/18 17:37
 * @remarks : #
 */
@RestController
@RequestMapping("user")
public class UserCheckController {
    @Autowired
    private UserCheckService checkService;

    @GetMapping("check")
    public Users check(String username){
        return checkService.checkLogin(username);
    }
}
