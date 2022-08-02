package com.wqddg.apiuserregist.service;

import com.wqddg.common.vo.ResultVo;

/**
 * @Author: wqddg
 * @ClassName UserResgistService
 * @DateTime: 2021/12/19 15:22
 * @remarks : #
 */
public interface UserResgistService {

    ResultVo regist(String username,String passwd);
}
