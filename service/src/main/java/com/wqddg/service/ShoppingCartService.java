package com.wqddg.service;

import com.wqddg.common.vo.ResultVo;
import com.wqddg.entity.ShoppingCart;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ShoppingCartService
 * @DateTime: 2021/11/19 22:29
 * @remarks : #
 */
public interface ShoppingCartService {
    public ResultVo addshoppingCart(ShoppingCart cart);
    public ResultVo findAllShoppingCart(String userId);


    ResultVo updataShoppingCart(Integer cartId,Integer cartNum);


    ResultVo listShoppingCartByCids(String cids);
}
