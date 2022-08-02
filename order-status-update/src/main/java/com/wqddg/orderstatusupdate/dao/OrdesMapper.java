package com.wqddg.orderstatusupdate.dao;

import com.wqddg.entity.Orders;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName OrdesMapper
 * @DateTime: 2021/12/20 22:18
 * @remarks : #
 */
@Repository
public interface OrdesMapper extends Mapper<Orders>, MySqlMapper<Orders> {
}
