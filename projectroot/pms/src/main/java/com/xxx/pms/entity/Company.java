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
     *   联系方式
     *
     */
    private String phone;

    /**
     *   公司logo头像
     */
    private String logo;

    /**
     *   1正常0禁用
     */
    private Boolean statue;


    /**
     *   管理员用户id
     *
     */
    private String adminId;

}