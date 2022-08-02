package com.wqdddg.stock_update.service;

import com.wqddg.entity.ProductSku;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName StockUpdateService
 * @DateTime: 2021/12/19 21:37
 * @remarks : #
 */
public interface StockUpdateService {
    int updateStock(List<ProductSku> skus);
}
