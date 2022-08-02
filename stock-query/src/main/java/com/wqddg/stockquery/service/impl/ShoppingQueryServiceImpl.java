package com.wqddg.stockquery.service.impl;

import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.stockquery.dao.ShoppingCartMapper;
import com.wqddg.stockquery.service.ShoppingQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ShoppingQueryServiceImpl
 * @DateTime: 2021/12/19 20:49
 * @remarks : #
 */
@Service
public class ShoppingQueryServiceImpl implements ShoppingQueryService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCartVo> selectShopcartByyCids(String lists) {
        //1,3,5,3,56,12,
        String[] split = lists.split(",");
        List<Integer> cartIds=new ArrayList<>();
        for (String s : split) {
            cartIds.add(Integer.parseInt(s));
        }
        List<ShoppingCartVo> shoppingCartVos = shoppingCartMapper.selectShopcartByyCids(cartIds);
        return shoppingCartVos;
    }
}
