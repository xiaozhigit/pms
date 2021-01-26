package com.xxx.pms.response;

import lombok.Data;

/**
 * 项目中用户正在执行的任务实体类
 */
@Data
public class ProjectProgressTask {
    private Integer userId;
    private String  name;
    private String image;
    private Integer taskId;
    private String context;
}
