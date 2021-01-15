package com.xxx.pms.service;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * webSocket服务类
 */
public interface WebSocketService {

    /**
     * 指定用户id发送消息给用户
     * @param userId 用户id
     * @param message 消息内容
     */
    int sendMessage(Integer userId,String message);

    /**
     * 批量发送消息
     * @param userIdList 用户id集合
     * @param message 消息内容
     */
    int sendMessagePatch(List<Integer> userIdList, String message);


    /**
     * 发送消息给所有在线用户
     * @param message 消息内容
     */
    int sendMessageAll(String message);

    /**
     * 发送消息给所有人出了自己
     * @param userId 当前用户id
     * @param message 消息内容
     * @return 发送是否成功
     */
    int sendMessageAllNoSelf(Integer userId,String message);
}
