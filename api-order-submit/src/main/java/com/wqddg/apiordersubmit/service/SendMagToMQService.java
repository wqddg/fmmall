package com.wqddg.apiordersubmit.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wqddg
 * @ClassName SendMagToMQ
 * @DateTime: 2021/12/24 12:12
 * @remarks : # 消息队列
 */
@Service
public class SendMagToMQService {
    @Autowired
    private AmqpTemplate amqpTemplate;


    public void setMsg(String orderId){
        amqpTemplate.convertAndSend("ex6","key1",orderId);
    }

}
