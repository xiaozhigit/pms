package com.xxx.pms.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static final AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static final ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();


    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        sessionPools.put(userId, session);
        addOnlineCount();
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "userId") String userId) {
        sessionPools.remove(userId);
        subOnlineCount();
//       msgCommon(SysConstant.MSG_TYPE_ONLINE,"有新用户下线了！"); // 用户下线群发通知
//        System.out.println(userName + "断开webSocket连接！当前人数为" + onlineNum);
    }


    //收到客户端信息
    @OnMessage
    public void onMessage(String message, Session session) {
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
        try {
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }


    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    //给指定用户发送信息
    public int sendInfo(String userId, String message) {
        Session session = sessionPools.get(userId);
        try {
            sendMessage(session, message);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //给多个用户发送信息
    public int sendInfo(List<Integer> userIdList, String message) {
        for (Integer userId : userIdList) {
            Session session = sessionPools.get(userId.toString());
            try {
                sendMessage(session, message);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }

    //发送给所有
    public int sendInfoAll(String message) {
        try {
            for (Map.Entry<String, Session> entry : sessionPools.entrySet()) {
                sendMessage(entry.getValue(), message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    //发送给所有除了自己
    public int sendInfoAllNoSelf(String message, Integer userId) {
        try {
            for (Map.Entry<String, Session> entry : sessionPools.entrySet()) {
                if (!entry.getKey().equals(userId.toString())) {
                    sendMessage(entry.getValue(), message);
                }
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
}
