package com.wqddg.userregist.service.impl;

import com.wqddg.entity.Users;
import com.wqddg.userregist.dao.UsersMapper;
import com.wqddg.userregist.service.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wqddg
 * @ClassName UserAddServiceImpl
 * @DateTime: 2021/12/19 13:33
 * @remarks : #
 */
@Service
public class UserAddServiceImpl implements UserAddService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int savaUser(Users users) {

        return usersMapper.insertUseGeneratedKeys(users);
    }
}
