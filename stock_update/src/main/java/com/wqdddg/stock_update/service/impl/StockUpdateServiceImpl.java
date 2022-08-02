package com.wqdddg.stock_update.service.impl;

import com.wqdddg.stock_update.dao.ProductSkuMapper;
import com.wqdddg.stock_update.service.StockUpdateService;
import com.wqddg.entity.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockUpdateServiceImpl
 * @DateTime: 2021/12/19 21:38
 * @remarks : #
 */
@Service
public class StockUpdateServiceImpl implements StockUpdateService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public int updateStock(List<ProductSku> skus) {
        int updateStatis=1;
        for (ProductSku productSku : skus) {
            int i = productSkuMapper.updateByPrimaryKeySelective(productSku);
            updateStatis*=i;
        }
        return updateStatis;
    }
}
