package com.wqddg.service.impl;

import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.ShoppingCartMapper;
import com.wqddg.entity.ShoppingCart;
import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.service.ShoppingCartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ShoppingCartServiceImpl
 * @DateTime: 2021/11/19 22:29
 * @remarks : #
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Override
    @Transactional
    public ResultVo addshoppingCart(ShoppingCart cart) {
        cart.setCartTime(format.format(new Date()));
        int insert_shopping = shoppingCartMapper.insert(cart);
        if (insert_shopping>0){
            return new ResultVo(ResStatus.OK,"success",null);
        }
        return new ResultVo(ResStatus.NO,"fail",null);
    }

    @Override
    public ResultVo findAllShoppingCart(String userId) {
        if (userId==null){
            return new ResultVo(ResStatus.NO,"id is not null",null);
        }
        List<ShoppingCartVo> shoppingCartVos = shoppingCartMapper.selectShopcartByUserId(userId);
        return new ResultVo(ResStatus.OK,"success",shoppingCartVos);
    }

    @Override
    @Transactional
    public ResultVo updataShoppingCart(Integer cartId, Integer cartNum) {
        int upadate_ = shoppingCartMapper.upadateShoppingCartByCartid(cartId, cartNum);
        if (upadate_>0){
            return new ResultVo(ResStatus.OK,"success",null);
        }else {
            return new ResultVo(ResStatus.NO,"exis",null);
        }

    }

    @Override
    public ResultVo listShoppingCartByCids(String cids) {
        String[] split = cids.split(",");

        List<Integer> cartIds=new ArrayList<>();
        for (String s : split) {
            cartIds.add(Integer.parseInt(s));
        }
        List<ShoppingCartVo> shoppingCartVos = shoppingCartMapper.selectShopcartByyCids(cartIds);
        return new ResultVo(ResStatus.OK,"success",shoppingCartVos);
    }
}
