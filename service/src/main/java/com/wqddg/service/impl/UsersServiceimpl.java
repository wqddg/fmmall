package com.wqddg.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.common.utils.Base64Utils;
import com.wqddg.common.utils.MD5Utils;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.UsersMapper;
import com.wqddg.entity.Users;
import com.wqddg.service.UsersService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName UsersService
 * @DateTime: 2021/11/13 23:00
 * @remarks : #
 */
@Service
public class UsersServiceimpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //用来将实体类转换为json字符串
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    @Transactional
    public ResultVo usersResgit(String username, String password) {
        synchronized (this){
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", username);
            Users users = usersMapper.selectOneByExample(example);
            if (users!=null){
                return new ResultVo(10001,"账户已存在", null);
            }
            String password_md5 = MD5Utils.md5(password);
            users=new Users();
            users.setUsername(username);
            users.setPassword(password_md5);
            users.setUserRegtime(new Date());
            users.setUserModtime(users.getUserRegtime());
            users.setUserImg("img/default.jpg");
            int i=usersMapper.insert(users);
            if (i>0){
                return new ResultVo(ResStatus.OK,"注册成功", users);
            }else {
                return new ResultVo(ResStatus.NO,"注册失败", null);
            }
        }
    }

    @Override
    public ResultVo checkLogin(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        Users users = usersMapper.selectOneByExample(example);
        if (users==null){
            return new ResultVo(ResStatus.NO,"账户登录失败", null);
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
