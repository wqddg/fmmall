package com.wqddg.apiordersubmit.service;

import com.wqddg.common.entity.Orders;

import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName OrderSubmitService
 * @DateTime: 2021/12/19 23:42
 * @remarks : #
 */
public interface OrderSubmitService {

    Map<String,String> addorder(String cids, Orders orders);
}
