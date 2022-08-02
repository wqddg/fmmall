package com.wqddg.shopcartdel.dao;

import com.wqddg.entity.ShoppingCart;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName ShoppingCartMapper
 * @DateTime: 2021/12/19 22:07
 * @remarks : #
 */
@Repository
public interface ShoppingCartMapper extends Mapper<ShoppingCart>, MySqlMapper<ShoppingCart> {
}
