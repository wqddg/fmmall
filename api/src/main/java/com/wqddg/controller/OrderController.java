package com.wqddg.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.service.timeJob.MyPayConfig;
import com.wqddg.entity.Orders;
import com.wqddg.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName OrderController
 * @DateTime: 2021/11/23 16:08
 * @remarks : #
 */
@RestController
@RequestMapping("order")
@Api(value = "提供订单的相关操作",tags = "订单接口")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("add")
    @ApiOperation(value = "提交我们的用户信息")
    public ResultVo add(String cids,@RequestBody Orders orders){
        ResultVo resultVo = orderService.addOrder(cids, orders);
        Map<String,String> orderMaps= (Map<String, String>) resultVo.getData();
        //设置当前订单信息
        try {
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
        }catch (Exception e){
            resultVo.setCode(ResStatus.NO);
            resultVo.setMsg("订单提交失败");
            return resultVo;
        }
        resultVo.setMsg("订单提交成功");
        resultVo.setData(orderMaps);
        return resultVo;
    }



    @GetMapping("/status/{oid}")
    @ApiOperation(value = "实时查看我们是否支付成功")
    @ApiImplicitParam(value = "订单的id",name = "oid",required = true,dataType = "string")
    public ResultVo getOrderStatus(@PathVariable("oid") String ordersId){
        return orderService.getOrderByid(ordersId);
    }


    @GetMapping("/list")
    @ApiOperation(value = "根据用户id 查询我们的订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户的id",name = "userId",required = true,dataType = "string"),
            @ApiImplicitParam(value = "订单的状态",name = "status",required = false,dataType = "string"),
            @ApiImplicitParam(value = "页码",name = "pageNum",required = true,dataType = "int"),
            @ApiImplicitParam(value = "每一页的条数",name = "limit",required = true,dataType = "int")
    })
    public ResultVo list(@RequestHeader("token")String token,String userId, String status, int pageNum, int limit){
        return orderService.listOrders(userId, status, pageNum, limit);
    }
}
