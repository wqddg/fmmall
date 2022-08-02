package com.wqddg.dao;

import com.wqddg.entity.Orders;
import com.wqddg.entity.OrdersVo;
import com.wqddg.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper extends GeneralDAO<Orders> {
    List<OrdersVo> selectOrders(@Param("userId") String userId,@Param("status") String status,@Param("start") int start,@Param("limit")int limit);
}