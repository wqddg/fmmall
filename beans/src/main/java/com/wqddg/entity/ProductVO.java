package com.wqddg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private String productId;

    private String productName;

    private Integer categoryId;

    private Integer rootCategoryId;

    private Integer soldNum;

    private Integer productStatus;

    private Date createTime;

    private Date updateTime;

    private String content;

    private List<ProductImg> items;//在查询商品的时候  关联查询商品图片信息

    private List<ProductSku> skus;//在查询商品的时候   关联查询商品套餐信息

}