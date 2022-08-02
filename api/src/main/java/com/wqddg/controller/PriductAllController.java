package com.wqddg.controller;

import com.wqddg.common.vo.ResultVo;
import com.wqddg.service.ProductCommentsService;
import com.wqddg.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wqddg
 * @ClassName PriductAllController
 * @DateTime: 2021/11/18 17:06
 * @remarks : #
 */
@RestController
@RequestMapping("product")
@Api(value = "提供商品信息相关的接口",tags = "商品详情")
public class PriductAllController {
    @Autowired
    private ProductService productService;



    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取商品详情接口")
    @ApiImplicitParam(value = "商品的id",name = "id",dataType = "string",required = true)
    public ResultVo findById(@PathVariable String id){
        return productService.getProductBaseicInfo(id);
    }
    @GetMapping("/detail-params/{id}")
    @ApiOperation(value = "获取商品参数详情接口")
    @ApiImplicitParam(value = "商品的id",name = "id",dataType = "string",required = true)
    public ResultVo findById_params(@PathVariable String id){
        return productService.getProductParamsByid(id);
    }



    @Autowired
    private ProductCommentsService commentsService;





    @GetMapping("/detail-comments/{id}")
    @ApiOperation(value = "获取商品评价详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品的id",name = "id",dataType = "string",required = true),
            @ApiImplicitParam(value = "商品的第几页",name = "pageNum",dataType = "integer",required = true),
            @ApiImplicitParam(value = "商品的一页多少",name = "limit",dataType = "integer",required = true)
    })
    public ResultVo findById_Comments(@PathVariable("id") String id, Integer pageNum,Integer limit){
        if (pageNum==null){
            pageNum=1;
        }
        if (limit==null){
            limit=5;
        }
        return commentsService.selectCommentsUsers(id,pageNum,limit);
    }


    @GetMapping("/detail-comments-count/{id}")
    @ApiOperation(value = "商品评价统计查询接口")
    @ApiImplicitParam(value = "商品的id",name = "id",dataType = "string",required = true)
    public ResultVo getProductCommontsCount(@PathVariable("id") String id){
        return commentsService.getCommentsCount(id);
    }


    @GetMapping("/listbycid/{id}")
    @ApiOperation(value = "分类商品大全")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品分类id",name = "id",required = true,dataType = "int"),
            @ApiImplicitParam(value = "每页显示的条数",name = "srtart",required = true,dataType = "int"),
            @ApiImplicitParam(value = "当前页码",name = "limit",required = true,dataType = "int"),
    })
    public ResultVo getProductCommonts(@PathVariable("id") int cid,int srtart,int limit){
        return productService.getProductByCategoryId(cid,srtart,limit);
    }

    @GetMapping("/listbrands/{id}")
    @ApiOperation(value = "根据类别ID查询商品品牌 ")
    ResultVo getProductBrands(@PathVariable("id") int cid){
        return productService.listBrands(cid);
    }


    @GetMapping("listsearch")
    @ApiOperation(value = "我们的搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "我们的搜索值",name = "keyword",dataType = "string",required = true),
            @ApiImplicitParam(value = "页数",name = "start",dataType = "int",required = true),
            @ApiImplicitParam(value = "每一页展示的效果",name = "limit",dataType = "int",required = true)
    })
    ResultVo getProductSearch(String keyword,  int start, int limit){
        return productService.searchProduct(keyword, start, limit);
    }


    @GetMapping("/listsearch-keyword")
    @ApiOperation(value = "根据类别名称查询商品品牌 ")
    @ApiImplicitParam(value = "我们的商品名称",name = "cid",dataType = "string",required = true)
    ResultVo getProductBrandsByKeyWrod( String cid){
        return productService.listBrands(cid);
    }



}
