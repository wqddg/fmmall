package com.wqddg.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.common.vo.PageHelper;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.ProductImgMapper;
import com.wqddg.dao.ProductMapper;
import com.wqddg.dao.ProductParamsMapper;
import com.wqddg.dao.ProductSkuMapper;
import com.wqddg.entity.*;
import com.wqddg.service.ProductService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.*;

/**
 * @Author: wqddg
 * @ClassName ProductServiceImpl
 * @DateTime: 2021/11/17 14:52
 * @remarks : #
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductParamsMapper productParamsMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RestHighLevelClient highLevelClient;


    private ObjectMapper mapper=new ObjectMapper();

    @Override
    public ResultVo findRecommend() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducets();
        return new ResultVo(ResStatus.OK,"完成",productVOS);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)  //商品的事务
    public ResultVo getProductBaseicInfo(String productId) {
        try {
            //根据商品id查询
            String productsinfo = (String) stringRedisTemplate.boundHashOps("products").get(productId);
            if (productsinfo!=null){
                Product product = mapper.readValue(productsinfo, Product.class);
                String productImgsString = (String) stringRedisTemplate.boundHashOps("productImgs").get(productId);
                JavaType javaType=mapper.getTypeFactory().constructParametricType(ArrayList.class,ProductImg.class);
                List<ProductImg> productImgs = mapper.readValue(productImgsString, javaType);
                String productSkusString = (String) stringRedisTemplate.boundHashOps("productSkus").get(productId);
                JavaType javaType_sku=mapper.getTypeFactory().constructParametricType(ArrayList.class,ProductSku.class);
                List<ProductSku> productSkus = mapper.readValue(productSkusString, javaType_sku);
                Map<String,Object> maps=new HashMap<>();
                maps.put("product",product);
                maps.put("productImgs",productImgs);
                maps.put("productSkus",productSkus);
                return new ResultVo(ResStatus.OK,"success",maps);
            }else {
                //商品基本信息
                Example example_product = new Example(Product.class);
                Example.Criteria criteria_product = example_product.createCriteria();
                criteria_product.andEqualTo("productId",productId).andEqualTo("productStatus",1);
                Product product = productMapper.selectOneByExample(example_product);
                if (product!=null){
                    stringRedisTemplate.boundHashOps("products").put(productId,mapper.writeValueAsString(product));
                    //商品图片
                    Example example_product_img = new Example(ProductImg.class);
                    Example.Criteria criteria_product_img = example_product_img.createCriteria();
                    criteria_product_img.andEqualTo("itemId", productId);
                    List<ProductImg> productImgs = productImgMapper.selectByExample(example_product_img);
                    stringRedisTemplate.boundHashOps("productImgs").put(productId,mapper.writeValueAsString(productImgs));
                    //商品套餐
                    Example example_ProductSku = new Example(ProductSku.class);
                    Example.Criteria criteria_ProductSku = example_ProductSku.createCriteria();
                    criteria_ProductSku.andEqualTo("productId",productId).andEqualTo("status",1);
                    List<ProductSku> productSkus = productSkuMapper.selectByExample(example_ProductSku);
                    stringRedisTemplate.boundHashOps("productSkus").put(productId,mapper.writeValueAsString(productSkus));
                    Map<String,Object> maps=new HashMap<>();
                    maps.put("product",product);
                    maps.put("productImgs",productImgs);
                    maps.put("productSkus",productSkus);
                    return new ResultVo(ResStatus.OK,"success",maps);
                }else {
                    return new ResultVo(ResStatus.NO,"查询商品不存在",null);
                }
            }
        }catch (Exception e){

        }
        return new ResultVo(ResStatus.NO,"查询商品不存在",null);
    }

    @Override
    public ResultVo getProductParamsByid(String productId) {
        Example example_ProductParams = new Example(ProductParams.class);
        Example.Criteria criteria_ProductParams = example_ProductParams.createCriteria();
        criteria_ProductParams.andEqualTo("productId",productId);
        ProductParams productParams = productParamsMapper.selectOneByExample(example_ProductParams);
        if (productParams==null){
            return new ResultVo(ResStatus.NO,"三无产品",null);
        }else {
            return new ResultVo(ResStatus.OK,"success",productParams);
        }
    }

    @Override
    public ResultVo getProductByCategoryId(int categoryId, int pageNum, int limit) {
        //查询分页数据
        int start=(pageNum-1)*limit;
        List<ProductVO> productVOS = productMapper.selectProductByCategoryId(categoryId, start, limit);
        //查询当前类别下的商品总记录数
        Example exampleProduct=new Example(Product.class);
        Example.Criteria criteriaProduct = exampleProduct.createCriteria();
        criteriaProduct.andEqualTo("categoryId",categoryId);
        int count = productMapper.selectCountByExample(exampleProduct);
        int pageCount=count%limit==0?count/limit:count/limit+1;
        PageHelper<ProductVO> productVOPageHelper = new PageHelper<>(count,pageCount, productVOS);
        return new ResultVo(ResStatus.OK,"success",productVOPageHelper);
    }

    @Override
    public ResultVo listBrands(int categoryId) {
        List<String> brands = productMapper.selectBrandBy(categoryId);
        return new ResultVo(ResStatus.OK,"success",brands);
    }

    /**
     * 通过我们的接口来开始查询我们的商品 这里使用的是数据库查询方式
     * @param keyword
     * @param start
     * @param limit
     * @return
     */
//    @Override
//    public ResultVo searchProduct(String keyword, int start, int limit) {
//        keyword="%"+keyword+"%";
//        start=(start-1)*limit;
//        List<ProductVO> productVOS = productMapper.selectProductByKeyword(keyword, start, limit);
//        //查询总记录数
//        Example exampleProduct = new Example(Product.class);
//        Example.Criteria exampleProductCriteria = exampleProduct.createCriteria();
//        exampleProductCriteria.andLike("productName",keyword);
//        int count = productMapper.selectCountByExample(exampleProduct);
//        //计算总页数
//        int pageCount=count%limit==0?count/limit:count/limit+1;
//        return new ResultVo(ResStatus.OK,"success",new PageHelper<ProductVO>(count,pageCount,productVOS));
//    }


    @Override
    public ResultVo searchProduct(String keyword, int start, int limit) {
        int starts=(start-1)*limit;
        try {
            //从es查询数据
            SearchRequest request=new SearchRequest("fmmall");
            SearchSourceBuilder builder=new SearchSourceBuilder();
            builder.query(QueryBuilders.multiMatchQuery(keyword,"productName","productSkuName"));

            builder.from(starts);
            builder.size(limit);


            HighlightBuilder highlightBuilder=new HighlightBuilder();
            HighlightBuilder.Field field1=new HighlightBuilder.Field("productName");
            HighlightBuilder.Field field2=new HighlightBuilder.Field("productSkuName");
            highlightBuilder.field(field1);
            highlightBuilder.field(field2);
            highlightBuilder.preTags("<label style='color:red'>");
            highlightBuilder.postTags("</label>");
            builder.highlighter(highlightBuilder);

            request.source(builder);
            SearchResponse search = highLevelClient.search(request, RequestOptions.DEFAULT);
            //封装查询结果
            SearchHits hits = search.getHits();
            //获取总记录数
            int count = (int)hits.getTotalHits().value;
            int pageCount=count%limit==0?count/limit:count/limit+1;
            Iterator<SearchHit> iterator = hits.iterator();
            List<Product4ES> product4ES=new ArrayList<>();
            while (iterator.hasNext()){
                SearchHit next = iterator.next();
                Product4ES product4E = mapper.readValue(next.getSourceAsString(), Product4ES.class);

                Map<String, HighlightField> highlightFields = next.getHighlightFields();
                HighlightField productName = highlightFields.get("productName");
                if (productName!=null){
                   String hight=Arrays.toString( productName.fragments());
                   product4E.setProductName(hight);
                }
                product4ES.add(product4E);
            }
            return new ResultVo(ResStatus.OK,"success",new PageHelper<Product4ES>(count,pageCount,product4ES));
        } catch (Exception e) {
            return new ResultVo(ResStatus.NO,"success",new PageHelper<Product4ES>(0,0,null));
        }
    }


    @Override
    public ResultVo listBrands(String kw) {
        kw="%"+kw+"%";
        List<String> list = productMapper.selectBrandKeyword(kw);
        return new ResultVo(ResStatus.OK,"success",list);
    }
}
