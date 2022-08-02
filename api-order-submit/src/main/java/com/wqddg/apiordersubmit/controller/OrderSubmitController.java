package com.wqddg.apiordersubmit.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.wqddg.apiordersubmit.config.MyPayConfig;
import com.wqddg.apiordersubmit.service.OrderSubmitService;
import com.wqddg.common.entity.Orders;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wqddg
 * @ClassName OrderSubmitController
 * @DateTime: 2021/12/19 23:36
 * @remarks : #
 */
@RestController
@CrossOrigin
@RequestMapping("order")
public class OrderSubmitController {

    @Autowired
    private OrderSubmitService orderSubmitService;

    @PostMapping("add")
    public ResultVo add(String cids,@RequestBody Orders orders){
        ResultVo resultVo=null;
        try {
            Map<String, String> orderMaps = orderSubmitService.addorder(cids, orders);
            orderMaps.put("order_id", UUID.randomUUID().toString().replace("-",""));
            if (orderMaps!=null){
                //设置当前订单信息
                //微信支付:申请支付连接
                //微信支付:申请支付连接
                WXPayConfig wxPayConfig=new MyPayConfig();
                WXPay wxPay=new WXPay(wxPayConfig);
                Map<String,String> data=new HashMap<>();
                data.put("body",orderMaps.get("product_names"));//商品描述
                data.put("out_trade_no",orderMaps.get("order_id"));//使用当前的订单编号作为交易编号
                data.put("fee_type","CNY");//支付币种
                data.put("total_fee","1");//支付金额  1分钱
                data.put("trade_type","NATIVE"); //支付类型
                data.put("notify_url","http://m5gdkb.natappfree.cc/pay/success"); //设置支付完成时的回调方法的接口
                Map<String, String> stringStringMap = wxPay.unifiedOrder(data);
                System.out.println(stringStringMap);//我们主要使用code_url=weixin://wxpay/bizpayurl?pr=GBfau1Gzz
                orderMaps.put("payUrl",stringStringMap.get("code_url"));
                return new ResultVo(ResStatus.OK,"提交订单成功！",orderMaps);
            }else {
                return new ResultVo(ResStatus.NO,"提交订单失败！",null);
            }
        }catch (Exception e){
            return new ResultVo(ResStatus.NO,"提交订单失败！",null);
        }
    }


}
