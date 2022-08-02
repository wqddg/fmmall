package com.wqddg.stockquery.service;

import com.wqddg.entity.ShoppingCartVo;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ShoppingQueryService
 * @DateTime: 2021/12/19 20:48
 * @remarks : #
 */
public interface ShoppingQueryService {
    /**
     * 查询我们的订单
     * @param lists
     * @return
     */
    List<ShoppingCartVo> selectShopcartByyCids(String lists);
}
