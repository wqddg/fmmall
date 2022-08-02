package com.wqddg.usercheck.service.impl;

import com.wqddg.entity.Users;
import com.wqddg.usercheck.dao.UsersMapper;
import com.wqddg.usercheck.service.UserCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName UserCheckServiceImpl
 * @DateTime: 2021/12/18 17:39
 * @remarks : #
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {
    @Autowired
    public UsersMapper usersMapper;

    @Override
    public Users checkLogin(String username) {
        Example example=new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        Users users = usersMapper.selectOneByExample(example);
        return users;
    }
}
