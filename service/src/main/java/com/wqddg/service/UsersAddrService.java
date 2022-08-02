package com.wqddg.service;

import com.wqddg.common.vo.ResultVo;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName UsersAddr
 * @DateTime: 2021/11/21 17:26
 * @remarks : #
 */
public interface UsersAddrService {
    ResultVo listAddrByUid(Integer userId);
}
