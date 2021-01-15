package com.xxx.pms.service.impl;

import com.xxx.pms.component.WebSocketServer;
import com.xxx.pms.service.WebSocketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Resource
    private WebSocketServer webSocketServer;

    @Override
    public int sendMessage(Integer userId, String message) {
        return webSocketServer.sendInfo(userId.toString(), message);
    }

    @Override
    public int sendMessagePatch(List<Integer> userIdList, String message) {
        return webSocketServer.sendInfo(userIdList, message);
    }

    @Override
    public int sendMessageAll(String message) {
        return webSocketServer.sendInfoAll(message);
    }

    @Override
    public int sendMessageAllNoSelf(Integer userId, String message) {
        return webSocketServer.sendInfoAllNoSelf(message, userId);
    }
}
