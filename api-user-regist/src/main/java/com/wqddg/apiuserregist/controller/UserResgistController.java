package com.wqddg.apiuserregist.controller;

import com.wqddg.apiuserregist.service.UserResgistService;
import com.wqddg.common.entity.Users;
import com.wqddg.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wqddg
 * @ClassName UserResgistContrlooer
 * @DateTime: 2021/12/19 15:43
 * @remarks : #
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserResgistController {
    @Autowired
    private UserResgistService resgistService;

    @PostMapping("regis")
    public ResultVo resgist(@RequestBody Users users){
        return resgistService.regist(users.getUsername(), users.getPassword());
    }
}
