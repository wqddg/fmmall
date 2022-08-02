package com.wqddg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.dao.ProductMapper;
import com.wqddg.entity.Product4ES;
import com.wqddg.entity.ProductSku;
import com.wqddg.entity.ProductVO;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ElasticSearch
 * @DateTime: 2021/12/14 16:17
 * @remarks : #
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ElasticSearch {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateIndex() throws IOException {
        //创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("fmmall");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        if (createIndexResponse.isAcknowledged()){
            System.out.println("成功");
        }
    }
    @Test
    public void testImportData() throws Exception {
        //从数据查询数据库
        List<ProductVO> productVOS = productMapper.selectAllEs();
        System.out.println(productVOS.size());
        //将查询到的数据添加到es中
        for (int i = 0; i < productVOS.size(); i++) {
            String productId = productVOS.get(i).getProductId();
            String productName = productVOS.get(i).getProductName();
            Integer soldNum = productVOS.get(i).getSoldNum();
            ProductSku productSku = null;
            if (productVOS.get(i).getSkus()==null||productVOS.get(i).getSkus().size()>0){
                productSku = productVOS.get(i).getSkus().get(0);
            }
            String skuName =productSku==null?"":productSku.getSkuName();
            String skuImg=productSku==null?"":productSku.getSkuImg();
            Integer sellPrice = productSku==null?0:productSku.getSellPrice();
            Product4ES product4ES=new Product4ES(productId,productName,soldNum,skuImg,skuName,sellPrice);
            IndexRequest request=new IndexRequest("fmmall");
            request.id(product4ES.getProductId());
            request.source(objectMapper.writeValueAsString(product4ES), XContentType.JSON);
            restHighLevelClient.index(request,RequestOptions.DEFAULT);
        }
    }
}
