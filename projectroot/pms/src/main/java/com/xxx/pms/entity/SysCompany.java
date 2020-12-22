package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 * This class corresponds to the database table sys_company
 */
@Data
@Table(name = "sys_company")
public class SysCompany {
    /**
     *   编号
     * @mbg.generated
     */
    @Id
    private String id;

    /**
     *   名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     *   地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     *   联系方式
     *
     * @mbg.generated
     */
    private String telPhone;

    /**
     *   公司logo头像
     * @mbg.generated
     */
    private String logo;

    /**
     *   邮箱
     *
     * @mbg.generated
     */
    private String email;

    /**
     *   0正常 1为禁用  2为停用
     * @mbg.generated
     */
    private Boolean delFlag;

    /**
     *   创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *   管理员用户id
     *
     * @mbg.generated
     */
    private String adminId;

    /**
     *   公司代码
     * @mbg.generated
     */
    private String code;

    /**
     *   公司当前编码
     * @mbg.generated
     */
    private String currentNumber;
}