package com.xxx.pms.util;

import com.sun.applet2.AppletParameters;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发送消息给链接socket用户的工具类
 */
public class WebSocketUtils {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
     */
    public static final ConcurrentHashMap<Integer, Session> sessionPools = new ConcurrentHashMap<>();

    /**
     * 向指定session发送消息
     * @param session
     * @param message
     * @throws IOException
     */
    public static void sendMessage(Session session, String message) {
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给指定用户发送消息
     * @param userId
     * @param message
     * @return
     */
    public static void sendMsgToUser(Integer userId, String message) {
        sendMessage(sessionPools.get(userId), message);
    }

    /**
     * 给多个用户发送消息
     * @param userIdList
     * @param message
     * @return
     */
    public static void sendMsgToUserList(List<Integer> userIdList, String message) {
        for (Integer userId : userIdList) {
            sendMessage(sessionPools.get(userId), message);
        }
    }

    /**
     * 给所有连上socket的用户推送消息
     * @param message
     */
    public static void sendMsgToAll(String message) {
        for (Map.Entry<Integer, Session> entry : sessionPools.entrySet()) {
            sendMessage(entry.getValue(), message);
        }

    }

    /**
     * 给除自己所有连上socket的用户推送消息
     * @param userId
     * @param message
     */

    public void sendMsgToAllNoSelf(Integer userId,String message) {
        for (Map.Entry<Integer, Session> entry : sessionPools.entrySet()) {
            if (!entry.getKey().equals(userId.toString())) {
                sendMessage(entry.getValue(), message);
            }
        }
    }
}
