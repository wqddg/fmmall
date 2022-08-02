package com.wqddg.shopcartdel.service.impl;

import com.wqddg.shopcartdel.dao.ShoppingCartMapper;
import com.wqddg.shopcartdel.service.ShopcartDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wqddg
 * @ClassName ShopcartDelServiceImpl
 * @DateTime: 2021/12/19 22:04
 * @remarks : #
 */
@Service
public class ShopcartDelServiceImpl implements ShopcartDelService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public int delectShopcart(String cids) {
        int delect=1;
        String[] arr = cids.split(",");
        for (int i = 0; i < arr.length; i++) {
            int cid=Integer.parseInt(arr[i]);
            int i1 = shoppingCartMapper.deleteByPrimaryKey(cid);
            delect*=i1;
        }
        return delect;
    }
}
