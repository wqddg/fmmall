package com.wqddg.controller;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeWithZoneIdSerializer;
import com.github.wxpay.sdk.WXPayUtil;
import com.wqddg.service.OrderService;
import com.wqddg.websocket.WebSockerServer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName PayController
 * @DateTime: 2021/11/23 19:35
 * @remarks : #
 */
@RestController
@RequestMapping("pay")
@Api(value = "我们的微信支付接口",tags = "微信订单下单接口")
public class PayController {

    @Autowired
    private OrderService orderService;
    /**
     * 当用户支付成功之后，微信支付平台就会请求这个接口  就会将支付状态的数据传递过来
     * 1.接收微信支付平台传递的数据(使用request的输入流接收)
     * 2.
     */
    @PostMapping("success")
    public String  success(HttpServletRequest request) throws Exception {
         ServletInputStream inputStream = request.getInputStream();
         byte[] bytes=new byte[1024];
         int len=1;
         StringBuilder builder=new StringBuilder();
         while ((len=inputStream.read(bytes))!=-1){
             builder.append(new String(bytes,0,len));
         }
         String s=builder.toString();
         //将微信回调回来的数据转换为map
        Map<String, String> maps = WXPayUtil.xmlToMap(s);
        if (maps!=null&&"success".equalsIgnoreCase(maps.get("result_code"))){
            //支付成功

            //2.修改订单状态
            int out_trade_no = orderService.UpdateOrderStatus(maps.get("out_trade_no"), "2");
            //通过WebSocket连接向前端推送信息
            WebSockerServer.sendMsg(maps.get("out_trade_no"),"1");
            if (out_trade_no>0){
                //3.响应微信支付平台   不响应就会多次给我们发送信息   频繁的响应我
                Map<String ,String> maps_success=new HashMap<>();
                maps_success.put("return_code","success");
                maps_success.put("return_msg","ok");
                maps_success.put("appid",maps.get("appid"));
                maps_success.put("result_code","success");
                return WXPayUtil.mapToXml(maps_success);
            }else {
                return null;
            }
        }
        return null;
    }
}
