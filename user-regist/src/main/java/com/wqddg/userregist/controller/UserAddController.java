package com.wqddg.userregist.controller;

import com.wqddg.entity.Users;
import com.wqddg.userregist.service.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: wqddg
 * @ClassName UserAddController
 * @DateTime: 2021/12/19 13:30
 * @remarks : #
 */
@RestController
@RequestMapping("user")
public class UserAddController {

    @Autowired
    private UserAddService userAddService;


    @PostMapping("save")
    public int save(@RequestBody Users users){
        return userAddService.savaUser(users);
    }
}
