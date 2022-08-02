package com.wqddg.orderclose.service.impl;

import com.wqddg.common.entity.OrderItem;
import com.wqddg.common.entity.Orders;
import com.wqddg.common.entity.ProductSku;
import com.wqddg.orderclose.feign.OrderItemQueryClient;
import com.wqddg.orderclose.feign.OrderStatusUpdateClient;
import com.wqddg.orderclose.feign.ProductSkuQuertClient;
import com.wqddg.orderclose.feign.StockUpdateClient;
import com.wqddg.orderclose.service.OrderCloseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName OrderCloseServiceImpl
 * @DateTime: 2021/12/20 22:24
 * @remarks : #
 */
@Service
public class OrderCloseServiceImpl implements OrderCloseService {

    @Autowired
    private OrderStatusUpdateClient orderStatusUpdateClient;

    @Autowired
    private OrderItemQueryClient orderItemQueryClient;

    @Autowired
    private ProductSkuQuertClient productSkuQuertClient;

    @Autowired
    private StockUpdateClient stockUpdateClient;

    @Override
    public int closeService(String orderId, int closeType) {
        //1.调用 ORDER-STATUS-UPDATE 服务 修改订单状态为6 closeType=1
        Orders orders=new Orders();
        orders.setOrderId(orderId);
        orders.setStatus("6");
        orders.setCloseType(closeType);
        int update = orderStatusUpdateClient.update(orders);
        if (update>0){
            //2.查询当前订单包含的商品消息(订单快照)
            List<OrderItem> query = orderItemQueryClient.query(orderId);
            if (query!=null||query.size()>0){
                //3.查询商品套餐的服务  PRODCCT-SKU-QUERY
                List<ProductSku> lists=new ArrayList<>();
                for (OrderItem orderItem : query) {
                    String skuId = orderItem.getSkuId();
                    //根据id查询当前商品的套餐消息
                    ProductSku productSku = productSkuQuertClient.queryProductSku(skuId);
                    if (productSku!=null){
                        int newStock=productSku.getStock()+orderItem.getBuyCounts();
                        productSku.setStock(newStock);
                        lists.add(productSku);
                    }
                }
                //4.还原库存   调用修改商品库存  原有的+现在有的  STOCK-UPDATE
                int update1 = stockUpdateClient.update(lists);
                return update1;
            }
        }
        return 0;
    }
}
