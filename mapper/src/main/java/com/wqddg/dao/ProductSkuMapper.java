package com.wqddg.dao;

import com.wqddg.entity.ProductSku;
import com.wqddg.general.GeneralDAO;

import java.util.List;

public interface ProductSkuMapper extends GeneralDAO<ProductSku> {


    /**
     * 根据商品id 查询当前商品中价格最低的套餐
     * @param productID
     * @return
     */
    public List<ProductSku> selectLowestPriceByProductId(String productID);
}