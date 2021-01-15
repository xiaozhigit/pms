package com.xxx.pms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Table;
import java.util.Date;

/**
 *   任务用户表
 */
@Data
@Table(name = "sys_task_user")
public class TaskUser {

    private Integer id;

    /**

     *   任务ID
     */
    private Integer taskId;

    /**

     *   责任人/关注人ID
     */
    private Integer userId;

    /**

     *   责任人/关注人昵称
     */
    private Integer userName;

    /**

     *   "zr"代表责任人 "gz"代表关注人
     */
    @ApiModelProperty(value = "zr代表责任人 gz代表关注人")
    private String userType;

    /**

     *   实际使用分钟数
     */
    @ApiModelProperty(value = "实际使用分钟数")
    private Short actualMinute;

    /**

     *   任务开始时间
     */
    private Date gmtStart;

    /**

     *   任务完成时间
     */
    private Date gmtFinish;

    /**

     *   -1没有开始任务，0开始任务进行中，1完成
     */
    @ApiModelProperty(value = "-1没有开始任务，0开始任务进行中，1完成")
    private Byte state;


}