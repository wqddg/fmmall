package com.wqddg.orderitemadd.service.impl;

import com.wqddg.entity.OrderItem;
import com.wqddg.entity.ShoppingCartVo;
import com.wqddg.orderitemadd.dao.OrderItemMapper;
import com.wqddg.orderitemadd.service.OrderItemAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: wqddg
 * @ClassName OrderItemAddServiceImpl
 * @DateTime: 2021/12/19 21:20
 * @remarks : #
 */
@Service
public class OrderItemAddServiceImpl implements OrderItemAddService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public int save(List<ShoppingCartVo> cartVos, String orderId) {
        int s=1;
        for (int i=0;i<cartVos.size();i++) {
            String itemId=System.currentTimeMillis()+""+(new Random().nextInt(89999)+10000);
            OrderItem orderItem = new OrderItem(itemId, orderId, cartVos.get(i).getProductId(), cartVos.get(i).getProductName(),
                    cartVos.get(i).getProductImg(), cartVos.get(i).getSkuId(), cartVos.get(i).getSkuName(), new BigDecimal(cartVos.get(i).getSellPrice()),
                    Integer.parseInt(cartVos.get(i).getCartNum()), new BigDecimal(cartVos.get(i).getSellPrice() * Integer.parseInt(cartVos.get(i).getCartNum())), new Date(), new Date(), 0);
            int insert=orderItemMapper.insert(orderItem);
            s*=insert;
        }
        return s;
    }
}
