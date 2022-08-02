package com.wqddg.dao;

import com.wqddg.entity.ProductImg;
import com.wqddg.general.GeneralDAO;

import java.util.List;

public interface ProductImgMapper extends GeneralDAO<ProductImg> {



    public List<ProductImg> selectProductImgFind(int product_id);


}