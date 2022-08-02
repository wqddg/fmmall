package com.wqddg.orderadd.dao;

import com.wqddg.entity.Orders;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName OrderMapper
 * @DateTime: 2021/12/19 23:30
 * @remarks : #
 */
@Repository
public interface OrderMapper extends Mapper<Orders>, MySqlMapper<Orders> {
}
