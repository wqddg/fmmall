package com.wqddg.service;

import com.wqddg.common.vo.ResultVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: wqddg
 * @ClassName ProductService
 * @DateTime: 2021/11/17 14:52
 * @remarks : #商品类
 */
public interface ProductService {


    public ResultVo findRecommend();
    ResultVo getProductBaseicInfo(String productId);


    public ResultVo getProductParamsByid(String productId);


    ResultVo getProductByCategoryId(int categoryId,int pageNum,int limit);


    ResultVo listBrands(int categoryId);


    ResultVo searchProduct(String keyword,  int start, int limit);


    ResultVo listBrands(String kw);
}
