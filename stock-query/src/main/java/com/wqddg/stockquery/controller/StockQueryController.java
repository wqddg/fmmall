package com.wqddg.stockquery.controller;

import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.stockquery.service.ShoppingQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockQueryController
 * @DateTime: 2021/12/19 20:52
 * @remarks : #
 */
@RestController
public class StockQueryController {

    @Autowired
    private ShoppingQueryService service;

    @GetMapping("stock/query")
    public List<ShoppingCartVo> query(String cids){
        return service.selectShopcartByyCids(cids);
    }
}
