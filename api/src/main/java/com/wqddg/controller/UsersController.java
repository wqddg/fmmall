package com.wqddg.controller;

import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.entity.Users;
import com.wqddg.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wqddg
 * @ClassName UserController
 * @DateTime: 2021/11/13 17:05
 * @remarks : #
 */
@RestController
@RequestMapping("user")
@Api(value = "提供用户的登录和注册接口",tags = "用户管理")
public class UsersController {

    @Autowired
    public UsersService usersService;

    @GetMapping("login")
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username",value = "用户登录账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password",value = "用户登录密码",required = true)
    })
    public ResultVo login(String username,String password){
        return usersService.checkLogin(username,password);
    }


    @PostMapping("regist")
    @ApiOperation("用户注册接口")
    public ResultVo Regin(@RequestBody Users users){

        return usersService.usersResgit(users.getUsername(),users.getPassword());
    }

    @ApiOperation("检验token是否过期接口")
    @GetMapping("/check")
    public ResultVo userTokenCheck(@RequestHeader("token")String token){
        return new ResultVo(ResStatus.OK,"success",null);
    }
}
