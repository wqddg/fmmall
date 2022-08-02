package com.wqddg;

import com.wqddg.dao.CategoryMapper;
import com.wqddg.dao.ProductMapper;
import com.wqddg.dao.ShoppingCartMapper;
import com.wqddg.entity.CategoryVo;
import com.wqddg.entity.ProductVO;
import com.wqddg.entity.ShoppingCartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ApiApplicationTests
 * @DateTime: 2021/11/16 21:33
 * @remarks : #
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ApiApplicationTests {

    @Autowired
    private CategoryMapper catalogManager;


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ShoppingCartMapper mapper;

    /**
     * 商场的查询
     */
    @Test
    public void findAll(){
        List<CategoryVo> categoryVos = catalogManager.selectFirstLevelCategories();
        for (CategoryVo categoryVo : categoryVos) {
            System.out.println(categoryVo);
        }
    }


    @Test
    public void findAll_Product(){
        List<ProductVO> productVOS =
                productMapper.selectRecommendProducets();
        for (ProductVO productVO : productVOS) {
            System.out.println(productVO);
        }
    }
    @Test
    public void findAll_Producta(){
        List<CategoryVo> productVOS =
                catalogManager.selectFirstLevelCategories();
        for (CategoryVo productVO : productVOS) {
            System.out.println(productVO);
        }
    }
    @Test
    public void findAll_Shopping(){
        List<ShoppingCartVo> productVOS =
                mapper.selectShopcartByUserId("9");
        for (ShoppingCartVo productVO : productVOS) {
            System.out.println(productVO);
        }
    }
}
