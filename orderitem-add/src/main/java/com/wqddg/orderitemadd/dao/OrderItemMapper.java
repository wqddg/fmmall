package com.wqddg.orderitemadd.dao;

import com.wqddg.entity.OrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName orderItemMapper
 * @DateTime: 2021/12/19 21:21
 * @remarks : #
 */
@Repository
public interface OrderItemMapper extends Mapper<OrderItem> , MySqlMapper<OrderItem> {

}
