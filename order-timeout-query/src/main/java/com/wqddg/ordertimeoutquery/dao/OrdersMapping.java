package com.wqddg.ordertimeoutquery.dao;

import com.wqddg.entity.Orders;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName OrdersMapping
 * @DateTime: 2021/12/20 22:08
 * @remarks : #
 */
@Repository
public interface OrdersMapping extends Mapper<Orders> , MySqlMapper<Orders> {
}
