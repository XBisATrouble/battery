package com.bupt.battery.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/api/websocket/{shopId}")
@Component
public class WebSocket {
    private Session session;
    private String shopId;

    private static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String,Session> sessionPool = new HashMap<>();
    private static int onlineCount = 0;

    @OnOpen
    public void onOpen(Session session, @PathParam(value="shopId")String shopId) {
        this.session = session;
        this.shopId = shopId;
        webSockets.add(this);
        addOnlineCount();
        System.out.println("PUT: "+ this.session);
        sessionPool.put(shopId, session);
        System.out.println("【websocket消息】有新的连接，总数为:"+webSockets.size());
    }

    @OnClose
    public void onClose() {
        subOnlineCount();
        webSockets.remove(this);
        System.out.println("REMOVE:" + session);
        sessionPool.remove(shopId, session);
        System.out.println("【websocket消息】连接断开，总数为:"+webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:"+message);
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for(WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送文本)
    public void sendTextMessage(String shopId, String message) {
        Session session = sessionPool.get(shopId);
        //System.out.println("---"+shopId+"--"+message);
        if (session != null) {
            try {
                // System.out.println("---"+shopId+"--"+message);
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送对象)
    public void sendObjMessage(String shopId, Object message) {
        Session session = sessionPool.get(shopId);
        if (session != null) {
            try {
                session.getAsyncRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int getOnlineCount() {
        return onlineCount;
    }

    private synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    private synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public synchronized Map getSessionPool() {
        return sessionPool;
    }

}