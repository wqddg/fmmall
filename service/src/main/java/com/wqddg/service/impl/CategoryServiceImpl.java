package com.wqddg.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.CategoryMapper;
import com.wqddg.entity.CategoryVo;
import com.wqddg.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName CategoryService
 * @DateTime: 2021/11/16 18:04
 * @remarks : #
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private ObjectMapper mapper=new ObjectMapper();

    @Autowired
    private CategoryMapper categoryMapper;

    private Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 查询分类列表
     * @return
     */
    @Override
    public ResultVo listCategories() {
        List<CategoryVo> categoryVos=null;
        try {
            String cateories = redisTemplate.boundValueOps("cateories").get();
            if (cateories!=null){
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, CategoryVo.class);
                categoryVos = mapper.readValue(cateories, javaType);
            }else {
                categoryVos = categoryMapper.selectAllCategories();
                redisTemplate.boundValueOps("cateories").set(mapper.writeValueAsString(categoryVos));
                redisTemplate.boundValueOps("cateories").expire(1, TimeUnit.DAYS);
            }
        }catch (Exception e){
            return new ResultVo(ResStatus.NO,"exxie",null);
        }
        return new ResultVo(ResStatus.OK,"success",categoryVos);
    }

    /**
     * 查询所有一级分类，同时查询当前销量最高的6个商品
     * @return
     */
    @Override
    public ResultVo listFirstLevelCategories() {
        logger.info("");
        return new ResultVo(ResStatus.OK,"success",categoryMapper.selectFirstLevelCategories());
    }
}
