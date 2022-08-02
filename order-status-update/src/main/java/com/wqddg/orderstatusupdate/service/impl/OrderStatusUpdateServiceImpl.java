package com.wqddg.orderstatusupdate.service.impl;

import com.wqddg.entity.Orders;

import com.wqddg.orderstatusupdate.dao.OrdesMapper;
import com.wqddg.orderstatusupdate.service.OrderStatusUpdateService;
import org.springframework.stereotype.Service;

/**
 * @Author: wqddg
 * @ClassName OrderStatusUpdateServiceImpl
 * @DateTime: 2021/12/20 22:16
 * @remarks : #
 */
@Service
public class OrderStatusUpdateServiceImpl implements OrderStatusUpdateService {

    private OrdesMapper ordesMapper;

    @Override
    public int ordersStatus(Orders orders) {
        return ordesMapper.updateByPrimaryKeySelective(orders);
    }
}
