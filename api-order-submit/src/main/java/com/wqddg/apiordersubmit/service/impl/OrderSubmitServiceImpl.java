package com.wqddg.apiordersubmit.service.impl;

import com.wqddg.apiordersubmit.feign.OrderAddClient;
import com.wqddg.apiordersubmit.feign.OrderItemAddClient;
import com.wqddg.apiordersubmit.feign.ShopCatDelClient;
import com.wqddg.apiordersubmit.feign.StockUpdateClient;
import com.wqddg.apiordersubmit.service.OrderSubmitService;
import com.wqddg.common.entity.Orders;
import com.wqddg.common.entity.ProductSku;
import com.wqddg.common.entity.ShoppingCartVo;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: wqddg
 * @ClassName OrderSubmitServiceImpl
 * @DateTime: 2021/12/19 23:43
 * @remarks : #
 */
@Service
public class OrderSubmitServiceImpl implements OrderSubmitService {
    @Autowired
    private OrderAddClient orderAddClient;

    @Autowired
    private OrderItemAddClient orderItemAddClient;

    @Autowired
    private StockUpdateClient stockUpdateClient;

    @Autowired
    private ShopCatDelClient shopCatDelClient;

    @Override
    public Map<String, String> addorder(String cids, Orders orders) {
        Map<String,String> maps=null;
        //1.保存订单  ORDER-ADD
        orders.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        List<ShoppingCartVo> shoppingCartVos = orderAddClient.saveOrder(orders, cids);

        if (shoppingCartVos != null) {
            //2.保存订单快照 ORDERITEM-ADD
            int i = orderItemAddClient.addorderItme(shoppingCartVos, cids);
            if (i > 0) {
                //3.修改库存  STOCK_UPDATE
                List<ProductSku> skus = new ArrayList<>();
                for (ShoppingCartVo shoppingCartVo : shoppingCartVos) {
                    String skuId = shoppingCartVo.getSkuId();
                    int newStock = shoppingCartVo.getSkuStock() - Integer.parseInt(shoppingCartVo.getCartNum());
                    ProductSku productSku = new ProductSku();
                    productSku.setSkuId(skuId);
                    productSku.setStock(newStock);
                    skus.add(productSku);
                }
                int update = stockUpdateClient.update(skus);
                System.out.println(update);
                if (update > 0) {
                    //4.删除购物车 SHOPCART-DEL
                    int delect = shopCatDelClient.delect(cids);
                    if (delect > 0) {
                        maps=new HashMap<>();
                        maps.put("order_id", cids);
                        String str="";
                        for (int index = 0; index < shoppingCartVos.size(); index++) {
                            str+=shoppingCartVos.get(index).getSkuName();
                            str=index==shoppingCartVos.size()-1?str:str+",";
                        }
                        maps.put("product_names", str);
                    }
                }
            }
        }
        return maps;
    }
}
