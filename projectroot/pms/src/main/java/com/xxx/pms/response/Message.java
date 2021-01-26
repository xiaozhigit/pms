package com.xxx.pms.response;

import com.xxx.pms.constant.MsgType;
import lombok.Data;

/**
 * 消息推送实体
 */
@Data
public class Message<T> {
    private MsgType msgType;
    private T data;
}
