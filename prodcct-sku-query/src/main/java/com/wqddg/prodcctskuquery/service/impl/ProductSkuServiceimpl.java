package com.wqddg.prodcctskuquery.service.impl;

import com.wqddg.entity.ProductSku;
import com.wqddg.prodcctskuquery.dao.ProductSkuMapper;
import com.wqddg.prodcctskuquery.service.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wqddg
 * @ClassName ProductSkuService
 * @DateTime: 2021/12/20 23:20
 * @remarks : #
 */
@Service
public class ProductSkuServiceimpl implements ProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Override
    public ProductSku queryProductSku(String productSkuId) {
        return productSkuMapper.selectByPrimaryKey(productSkuId);
    }
}
