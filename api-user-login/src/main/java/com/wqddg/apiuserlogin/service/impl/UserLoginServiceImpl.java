package com.wqddg.apiuserlogin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.apiuserlogin.fiegn.UserCheck;
import com.wqddg.apiuserlogin.service.UserLoginService;
import com.wqddg.common.entity.Users;
import com.wqddg.common.utils.MD5Utils;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName UserLoginServiceImpl
 * @DateTime: 2021/12/18 17:05
 * @remarks : #
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserCheck check;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResultVo checkLogin(String username, String password) {
        //1.调用user-check服务根据用户名查询用户消息
        Users users = this.check.check(username);
        //2.进行用户消息的校验
        if (users==null){
            return new ResultVo(10001,"login fail ...",null);
        }else {
            if (users.getPassword().equals(MD5Utils.md5(password))){
                //如果登陆效验成功 则需要生成令牌token(按照特定规则生成的字符串)
                JwtBuilder builder = Jwts.builder();
                HashMap<String, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("wqddgs",users.getPassword());
                String token = builder
                        .setSubject(username)  //放置用户信息
                        .setIssuedAt(new Date()) //设置token的生成时间
                        .setId(users.getUserId() + "")//设置用户id为token id
                        .setClaims(objectObjectHashMap)//map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) //设置token的过期日期一天的间隔
                        .signWith(SignatureAlgorithm.HS256, "wqddg")
//                        .claim("key","value")  //添加自定义数据
                        .compact();//设置加密方式和加密密码
                //当用户登录成功之后 以token为key，将用户信息保存到redis
                String userinfo= null;
                try {
                    userinfo = objectMapper.writeValueAsString(users);
                } catch (JsonProcessingException e) {
                    return new ResultVo(ResStatus.NO,"账户登录失败 密码失败", null);
                }
                stringRedisTemplate.boundValueOps(token).set(userinfo,30, TimeUnit.MINUTES);
                return new ResultVo(ResStatus.OK,token,users);
            }else {
                return new ResultVo(ResStatus.NO,"账户登录失败 密码失败", null);
            }
        }
    }
}
