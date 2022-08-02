package com.wqddg.stockquery.dao;

import com.wqddg.entity.ShoppingCart;
import com.wqddg.entity.ShoppingCartVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ShopCatMapper
 * @DateTime: 2021/12/19 20:42
 * @remarks : #
 */
@Repository
public interface ShoppingCartMapper extends Mapper<ShoppingCart>, MySqlMapper<ShoppingCartVo> {
    /**
     * 查询我们的订单
     * @return
     */
    List<ShoppingCartVo> selectShopcartByyCids(List<Integer> list);
}
