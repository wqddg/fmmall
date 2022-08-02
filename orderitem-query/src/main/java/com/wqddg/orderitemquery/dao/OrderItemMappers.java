package com.wqddg.orderitemquery.dao;

import com.wqddg.entity.OrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: wqddg
 * @ClassName OrderItemMappers
 * @DateTime: 2021/12/20 22:41
 * @remarks : #
 */
@Repository
public interface OrderItemMappers extends Mapper<OrderItem>, MySqlMapper<OrderItem> {
}
