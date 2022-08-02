package com.wqddg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;


/**
 * 首页类别商品推荐
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo2 {
    private Integer categoryId;

    private String categoryName;

    private Integer categoryLevel;

    private Integer parentId;

    private String categoryIcon;


    private String categorySlogan;


    private String categoryPic;


    private String categoryBgColor;

    //商品
    private List<ProductVO> products;



}