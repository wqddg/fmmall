package com.wqddg.orderadd.feign.impl;

import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.orderadd.feign.StockQueryClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockQueryClientImpl
 * @DateTime: 2021/12/19 23:13
 * @remarks : #
 */
@Component
public class StockQueryClientImpl implements StockQueryClient {
    @Override
    public List<ShoppingCartVo> query(String cids) {
        System.out.println("stock-Query 服务降级");
        return null;
    }
}
