package com.wqddg.service.impl;

import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.UserAddrMapper;
import com.wqddg.entity.UserAddr;
import com.wqddg.service.UsersAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName UsersAddr
 * @DateTime: 2021/11/21 17:26
 * @remarks : #
 */
@Service
public class UsersAddrServiceImpl implements UsersAddrService {


    @Autowired
    private UserAddrMapper userAddrMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo listAddrByUid(Integer userId) {
        Example example_UserAddr = new Example(UserAddr.class);
        Example.Criteria criteria_UserAddr = example_UserAddr.createCriteria();
        criteria_UserAddr.andEqualTo("userId",userId).andEqualTo("status",1);
        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example_UserAddr);
        return new ResultVo(ResStatus.OK,"success",userAddrs);
    }
}
