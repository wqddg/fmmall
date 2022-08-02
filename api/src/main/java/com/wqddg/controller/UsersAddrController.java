package com.wqddg.controller;

import com.wqddg.common.vo.ResultVo;
import com.wqddg.service.UsersAddrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wqddg
 * @ClassName UsersAddrController
 * @DateTime: 2021/11/21 17:32
 * @remarks : #
 */
@RestController
@RequestMapping("useraddr")
@Api(value = "提供收获地址相关的接口",tags = "收获地址管理")
public class UsersAddrController {
    @Autowired
    private UsersAddrService usersAddrService;

    @GetMapping("list")
    @ApiImplicitParam(value = "用户id",name = "userId",dataType = "int" ,required = true)
    public ResultVo listAddr(Integer userId, @RequestHeader("token")String token){
        return usersAddrService.listAddrByUid(userId);
    }
}
