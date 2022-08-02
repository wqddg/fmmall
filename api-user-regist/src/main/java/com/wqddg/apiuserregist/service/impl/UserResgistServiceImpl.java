package com.wqddg.apiuserregist.service.impl;

import com.wqddg.apiuserregist.feign.UserCheckClient;
import com.wqddg.apiuserregist.feign.UserRegistClient;
import com.wqddg.apiuserregist.service.UserResgistService;
import com.wqddg.common.entity.Users;
import com.wqddg.common.utils.MD5Utils;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: wqddg
 * @ClassName UserResgistServiceImpl
 * @DateTime: 2021/12/19 15:24
 * @remarks : #
 */
@Service
public class UserResgistServiceImpl implements UserResgistService {

    @Autowired
    private UserCheckClient client;

    @Autowired
    private UserRegistClient registClient;

    @Override
    public ResultVo regist(String username, String password) {
        Users check = client.check(username);
        if (check==null){
            String password_md5 = MD5Utils.md5(password);
            Users users=new Users();
            users.setUsername(username);
            users.setPassword(password_md5);
            users.setUserRegtime(new Date());
            users.setUserModtime(users.getUserRegtime());
            users.setUserImg("img/default.jpg");
            int regis = registClient.regis(users);
            if (regis>0){
                return new ResultVo(ResStatus.OK,"注册成功", users);
            }else {
                return new ResultVo(ResStatus.NO,"注册失败", null);
            }
        }
        return new ResultVo(10001,"账户已存在", null);
    }
}
