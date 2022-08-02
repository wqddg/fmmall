package com.wqdddg.stock_update.dao;

import com.wqddg.entity.ProductSku;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName ProductSkuMapper
 * @DateTime: 2021/12/19 21:39
 * @remarks : #
 */
@Repository
public interface ProductSkuMapper extends Mapper<ProductSku>, MySqlMapper<ProductSku> {
}
