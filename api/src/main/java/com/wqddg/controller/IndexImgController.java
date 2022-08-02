package com.wqddg.controller;

import com.wqddg.common.vo.ResultVo;
import com.wqddg.service.CategoryService;
import com.wqddg.service.IndexImgService;
import com.wqddg.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName IndexImgController
 * @DateTime: 2021/11/16 15:57
 * @remarks : #
 */
@RestController
@RequestMapping("index")
@Api(value = "提供给主页的接口",tags = "主页接口")
public class IndexImgController {

    @Autowired
    private IndexImgService indexImgService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @GetMapping("indexImgs")
    @ApiOperation(value = "轮播图图片集合")
    public ResultVo findAll(){
        return indexImgService.findAll();
    }


    @GetMapping("category_list")
    @ApiOperation(value = "商品分类集合大全")
    public ResultVo findCategory(){
        return categoryService.listCategories();
    }



    @GetMapping("product_recommend_list")
    @ApiOperation(value = "商品推荐集合大全")
    public ResultVo findProductService(){
        return productService.findRecommend();
    }

    @GetMapping("product_listFirstLevelCategories")
    @ApiOperation(value = "独立商品推荐集合 6个")
    public ResultVo listFirstLevelCategories(){
        return categoryService.listFirstLevelCategories();
    }

}
