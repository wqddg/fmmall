package com.wqddg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wqddg
 * @ClassName Product4ES
 * @DateTime: 2021/12/14 17:00
 * @remarks : #
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product4ES {

    private String productId;

    private String productName;

    private int soldNum;

    private String productImg;

    private String productSkuName;

    private double productSkuPrice;

}
