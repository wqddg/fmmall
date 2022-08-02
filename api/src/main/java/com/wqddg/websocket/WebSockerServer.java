package com.wqddg.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wqddg
 * @ClassName WebSockerServer
 * @DateTime: 2021/11/23 23:14
 * @remarks : #
 */
@Component
//怎么接收别人的请求
@ServerEndpoint("/webSocket/{oid}")
public class WebSockerServer {
    //静态的容器
    private static ConcurrentHashMap<String,Session> sessionMap=new ConcurrentHashMap<>();

    /**
     * 前端发送请求建立webSocket连接、就会执行@OnOpen方法
     */
    @OnOpen
    public void open(@PathParam("oid") String orderId, Session session){
        System.out.println("建立连接"+orderId);
        sessionMap.put(orderId,session);
    }

    /**
     * 前端关闭了界面 或者主动关闭了连接 都会执行
     */
    @OnClose
    public void close(@PathParam("oid") String orderId){
        System.out.println("关闭连接"+orderId);
        sessionMap.remove(orderId);
    }


    public static void sendMsg(String orderId,String msg){
        try {
            Session session = sessionMap.get(orderId);
            session.getBasicRemote().sendText(msg);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
