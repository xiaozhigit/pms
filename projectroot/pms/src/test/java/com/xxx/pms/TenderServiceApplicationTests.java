package com.xxx.pms;


import com.xxx.pms.service.WebSocketService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TenderServiceApplicationTests {

    @Resource
    private WebSocketService webSocketService;

    // 发送单条消息
    @org.junit.Test
    public void contextLoads() throws Exception {
        webSocketService.sendMessage(12,"hello world");
    }





}
