package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.pojo.UserInfo;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wangxudong
 * @date 2020/10/28 11:23
 */
@ServerEndpoint(value = "/websocketConnect")
@Component
public class WebSocketConnect {

    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketConnect> webSocketSet = new CopyOnWriteArraySet<WebSocketConnect>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this); // 加入set中
        broadcast("WebSocket 已连接");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); // 从set中删除
        broadcast("WebSocket 已断开");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message) {
        broadcast("实时数据：" + message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     */
    public void broadcast(String message){
        for (WebSocketConnect item : webSocketSet) {
            // getAsyncRemote()和getBasicRemote()是异步与同步的区别
//            this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }

}
