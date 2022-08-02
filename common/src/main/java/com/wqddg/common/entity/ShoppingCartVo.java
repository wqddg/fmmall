package com.wqddg.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *  新增可productName 和productImg
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVo {

    private Integer cartId;

    private String productId;

    private String skuId;

    private String userId;

    private String cartNum;

    private String cartTime;

    private BigDecimal productPrice;

    private String skuProps;

    private String productName;
    private String productImg;
    private double originalPrice;
    private double sellPrice;
    private String skuName;

    //库存
    private int skuStock;
}