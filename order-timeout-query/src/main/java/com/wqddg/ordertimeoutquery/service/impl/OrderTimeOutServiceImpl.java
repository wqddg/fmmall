package com.wqddg.ordertimeoutquery.service.impl;

import com.wqddg.entity.Orders;
import com.wqddg.ordertimeoutquery.dao.OrdersMapping;
import com.wqddg.ordertimeoutquery.service.OrderTimeOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderTimeOutServiceImpl
 * @DateTime: 2021/12/20 22:07
 * @remarks : #
 */
@Service
public class OrderTimeOutServiceImpl implements OrderTimeOutService {
    @Autowired
    private OrdersMapping ordersMapping;

    @Override
    public List<Orders> query() {
        Example example=new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        Date time = new Date(System.currentTimeMillis() - 30 * 60 * 1000);
        criteria.andLessThan("createTime", time);
        List<Orders> orders = ordersMapping.selectByExample(example);
        return orders;
    }
}
