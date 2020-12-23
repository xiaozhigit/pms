package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 */
@Data
@Table(name = "sys_company")
public class Company {
    /**
     *   编号
     */
    @Id
    private String id;

    /**
     *   名称
     *
     */
    private String name;

    /**
     *   地址
     *
     */
    private String address;

    /**
     *   联系方式
     *
     */
    private String telPhone;

    /**
     *   公司logo头像
     */
    private String logo;

    /**
     *   邮箱
     *
     */
    private String email;

    /**
     *   0正常 1为禁用  2为停用
     */
    private Boolean delFlag;

    /**
     *   创建时间
     *
     */
    private Date createTime;

    /**
     *   管理员用户id
     *
     */
    private String adminId;

    /**
     *   公司代码
     */
    private String code;

    /**
     *   公司当前编码
     */
    private String currentNumber;
}