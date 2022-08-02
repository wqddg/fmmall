package com.wqddg.prodcctskuquery.controller;

import com.wqddg.entity.ProductSku;
import com.wqddg.prodcctskuquery.service.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName ProductSkuController
 * @DateTime: 2021/12/20 23:18
 * @remarks : #
 */
@RestController
public class ProductSkuController {

    @Autowired
    private ProductSkuService productSkuService;


    @GetMapping("product/sku/query")
    public ProductSku queryProductSku(String skuId){
        return productSkuService.queryProductSku(skuId);
    }
}
