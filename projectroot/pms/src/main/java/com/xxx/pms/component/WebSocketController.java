package com.xxx.pms.component;

import com.xxx.pms.util.WebSocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Slf4j
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketController {
    private static final long MAX_TIME_OUT = 24*60*60*1000;

    /**
     * 客户端建立链接
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") Integer userId) {
        session.setMaxIdleTimeout(MAX_TIME_OUT);
        System.out.println(session.getMaxIdleTimeout());
        WebSocketUtils.sessionPools.put(userId, session);
    }

    /**
     * 客户端断开链接
     * @param userId
     */
    @OnClose
    public void onClose(@PathParam(value = "userId") Integer userId) {
        System.out.println("close");
        WebSocketUtils.sessionPools.remove(userId);
    }

    /**
     * 发生错误的时候调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

    /**
     * 收到客户的消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
//        WebSocketUtils.sendMessage(session, message);
    }

}
