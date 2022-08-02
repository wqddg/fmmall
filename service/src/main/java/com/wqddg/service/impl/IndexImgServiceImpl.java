package com.wqddg.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.dao.IndexImgMapper;
import com.wqddg.entity.IndexImg;
import com.wqddg.entity.ProductSku;
import com.wqddg.service.IndexImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName IndexImgService
 * @DateTime: 2021/11/16 15:52
 * @remarks : #
 */
@Service
public class IndexImgServiceImpl implements IndexImgService {
    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private IndexImgMapper indexImgMapper;

    private ObjectMapper mapper=new ObjectMapper();
    @Override
    public ResultVo findAll() {
        List<IndexImg> indexImgs=null;
        try {
            String indexImage = template.opsForValue().get("indexImage");
            if (indexImage!=null){
                JavaType javaType_sku=mapper.getTypeFactory().constructParametricType(ArrayList.class, IndexImg.class);
                indexImgs = mapper.readValue(indexImage, javaType_sku);
                return new ResultVo(ResStatus.OK, "成功", indexImgs);
            }else {
                //1000个请求都会进入else(service类在Spring容器中是单例的，1000个并发会启动1000个线程来处理 但是公用一个service实例)
                synchronized (this){
                    String s = template.opsForValue().get("indexImage");
                    if (s==null){//在1000个请求中，只有第一个请求再次查询redis时依然为null
                        indexImgs = indexImgMapper.listIndexImags();
                        if (indexImgs.size()==0){
                            List<IndexImg> lists=new ArrayList<>();
                            template.boundValueOps("indexImage").set(mapper.writeValueAsString(lists));
                            //设置过期时间
                            template.boundValueOps("indexImage").expire(10, TimeUnit.SECONDS);
                            return new ResultVo(ResStatus.NO, "失败", null);
                        }else {
                            template.boundValueOps("indexImage").set(mapper.writeValueAsString(indexImgs));
                            //设置过期时间
                            template.boundValueOps("indexImage").expire(1, TimeUnit.DAYS);
                        }
                    }else {
                        JavaType javaType_sku=mapper.getTypeFactory().constructParametricType(ArrayList.class, IndexImg.class);
                        indexImgs = mapper.readValue(s, javaType_sku);
                        return new ResultVo(ResStatus.OK, "成功", indexImgs);
                    }
                return new ResultVo(ResStatus.OK, "成功", indexImgs);
                }
            }
        }catch (Exception e){
            return new ResultVo(ResStatus.NO, "失败", null);
        }
    }
}
