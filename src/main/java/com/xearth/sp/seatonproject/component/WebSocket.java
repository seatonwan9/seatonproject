package com.xearth.sp.seatonproject.component;

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
@ServerEndpoint(value = "/websocket/{nickName}")
@Component
public class WebSocket {

    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    // 用session作为key，保存用户信息
    private static Map<Session, UserInfo> connectmap = new HashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickName") String nickName) {
        this.session = session;
        UserInfo userInfo = new UserInfo(session.getId(), nickName);
        connectmap.put(session, userInfo);
        webSocketSet.add(this); // 加入set中
        System.out.println(nickName + " 上线了！当前在线人数为" + webSocketSet.size());
        // 群发消息，告诉每一位
        broadcast(nickName + " 上线了！-->当前在线人数为：" + webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String nickName = connectmap.get(session).getNickName();
        connectmap.remove(session);
        webSocketSet.remove(this); // 从set中删除
        System.out.println(nickName + " 下线了！当前在线人数为" + webSocketSet.size());
        // 群发消息，告诉每一位
        broadcast(nickName + " 下线，当前在线人数为：" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        // 群发消息
        String nickName = connectmap.get(session).getNickName();
        broadcast(nickName + " 说：" + message);
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
        for (WebSocket item : webSocketSet) {
            /* getAsyncRemote()和getBasicRemote()是异步与同步的区别 */
//            this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }

}
