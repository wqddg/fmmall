package com.wqddg.service.impl;

import com.wqddg.common.vo.PageHelper;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.ProductCommentsMapper;
import com.wqddg.entity.ProductComments;
import com.wqddg.entity.ProductCommentsVO;
import com.wqddg.service.ProductCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName ProductCommentsServiceImpl
 * @DateTime: 2021/11/19 12:03
 * @remarks : #
 */
@Service
public class ProductCommentsServiceImpl implements ProductCommentsService{


    @Autowired
    private ProductCommentsMapper productCommentsMapper;
    @Override
    public ResultVo selectCommentsUsers(String productId,int pageNum,int limit) {
        //查询所有的评论
//        return new ResultVo(ResStatus.OK,"success",productCommentsMapper.selectCommentsVo(productId));
        //分页查询评论
        //1.根据商品查询总总记录数
        Example example_ProductComments = new Example(ProductComments.class);
        Example.Criteria criteria_ProductComments = example_ProductComments.createCriteria();
        criteria_ProductComments.andEqualTo("productId",productId);
        int count = productCommentsMapper.selectCountByExample(example_ProductComments);
        //计算总页数   必须直到每页显示多少条 pageSize=limit
        int pageCount=count%limit==0?count/limit:count/limit+1;
        //查询当前叶的数据    获取页面的算法
        int start=(pageNum-1)*limit;
        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommentsVo(productId, start, limit);
        return new ResultVo(ResStatus.OK,"success",new PageHelper<ProductCommentsVO>(count,pageCount,productCommentsVOS));
    }

    @Override
    public ResultVo getCommentsCount(String productId) {
        //1.查询当前商品评价的总数
        Example example_ProductComments = new Example(ProductComments.class);
        Example.Criteria criteria_ProductComments = example_ProductComments.createCriteria();
        criteria_ProductComments.andEqualTo("productId",productId);
        int total=productCommentsMapper.selectCountByExample(example_ProductComments);
        //2.查询当前商品的好评数
        criteria_ProductComments.andEqualTo("commType",1);
        int goodTotal=productCommentsMapper.selectCountByExample(example_ProductComments);
        //3.查询当前商品的中评数
        Example example_ProductComments_1 = new Example(ProductComments.class);
        Example.Criteria criteria_ProductComments_1 = example_ProductComments_1.createCriteria();
        criteria_ProductComments_1.andEqualTo("productId",productId);
        criteria_ProductComments_1.andEqualTo("commType",0);
        int midTotal=productCommentsMapper.selectCountByExample(example_ProductComments_1);
        //4.查询当前商品的差评数
        Example example_ProductComments_2 = new Example(ProductComments.class);
        Example.Criteria criteria_ProductComments_2 = example_ProductComments_2.createCriteria();
        criteria_ProductComments_2.andEqualTo("productId",productId);
        criteria_ProductComments_2.andEqualTo("commType",-1);
        int badTotal=productCommentsMapper.selectCountByExample(example_ProductComments_2);
        //5.计算好评率
        double percent=(Double.parseDouble(goodTotal+"") / Double.parseDouble(total+""))*100;
        String percentValue=(percent+"").substring(0,(percent+"").lastIndexOf(".")+3);
        Map<String,Object> maps=new HashMap<>();
        maps.put("total",total);
        maps.put("goodTotal",goodTotal);
        maps.put("midTotal",midTotal);
        maps.put("badTotal",badTotal);
        maps.put("percent",percentValue);
        return new ResultVo(ResStatus.OK,"suess",maps);
    }
}
