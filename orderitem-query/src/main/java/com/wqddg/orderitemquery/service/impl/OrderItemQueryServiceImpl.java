package com.wqddg.orderitemquery.service.impl;

import com.wqddg.entity.OrderItem;
import com.wqddg.orderitemquery.dao.OrderItemMappers;
import com.wqddg.orderitemquery.service.OrderItemQueryService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderItemQueryServiceImpl
 * @DateTime: 2021/12/20 22:40
 * @remarks : #
 */
@Service
public class OrderItemQueryServiceImpl implements OrderItemQueryService {

    @Autowired
    private OrderItemMappers mappers;
    @Override
    public List<OrderItem> query(String orderId) {
        Example example=new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",orderId);
        return    mappers.selectByExample(example);
    }
}
