package com.wqddg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    private Integer categoryId;


    private String categoryName;


    private Integer categoryLevel;

    private Integer parentId;


    private String categoryIcon;


    private String categorySlogan;


    private String categoryPic;


    private String categoryBgColor;

    /**
     * 添加一个list用来设置信息
     */
    private List<CategoryVo> categoryVos;

    /**
     * 添加一个list来设置首页商品推荐
     */
    private List<ProductVO> productVOS;

}