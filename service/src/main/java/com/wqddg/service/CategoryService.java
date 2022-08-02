package com.wqddg.service;

import com.wqddg.common.vo.ResultVo;

/**
 * @Author: wqddg
 * @ClassName CategoryService
 * @DateTime: 2021/11/16 18:04
 * @remarks : #
 */
public interface CategoryService {

    public ResultVo listCategories();



    public ResultVo listFirstLevelCategories();
}
