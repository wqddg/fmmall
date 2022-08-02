package com.wqdddg.stock_update.controller;

import com.wqdddg.stock_update.service.StockUpdateService;
import com.wqddg.entity.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockUpdateController
 * @DateTime: 2021/12/19 21:36
 * @remarks : #
 */
@RestController
public class StockUpdateController {
    @Autowired
    private StockUpdateService service;

    @PostMapping("/stock/update")
    public int update(@RequestBody List<ProductSku> skus){
        return service.updateStock(skus);
    }
}
