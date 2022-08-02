package com.wqddg.dao;

import com.wqddg.entity.Product;
import com.wqddg.entity.ProductVO;
import com.wqddg.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends GeneralDAO<Product> {
    public List<ProductVO> selectRecommendProducets();

    //查询指定一级类别下的六个商品
    public List<ProductVO> selectTop6ByCategory(int cid);


    /**
     * 根据三级分类Id分页查询商品信息
     * @param cid 三级分类id
     * @param start 起始索引
     * @param pageSize 查询记录数
     * @return
     */
    List<ProductVO> selectProductByCategoryId(@Param("cid") int cid,@Param("start") int start,@Param("pageSize") int pageSize);

    /**
     * 在类别id中查询此类别下的商品的品牌列表
     * @param cid
     * @return
     */
    List<String> selectBrandBy(@Param("cid") int cid);

    List<ProductVO> selectProductByKeyword(@Param("keyword")String keyword,@Param("start") int start,@Param("limit") int limit);

    List<String> selectBrandKeyword(String keyWord);



    List<ProductVO> selectAllEs();
}