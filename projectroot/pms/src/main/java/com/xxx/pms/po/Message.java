package com.xxx.pms.po;

import com.xxx.pms.constant.MsgType;
import lombok.Data;

/**
 * 消息推送实体
 */
@Data
public class Message {
    private Integer id;
    private MsgType msgType;
    private Integer projectId;
    private Integer taskId;
    private Integer userId;
    private Byte tasStatus;
    private String content;
}
