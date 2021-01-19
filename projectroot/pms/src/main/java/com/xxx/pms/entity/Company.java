package com.xxx.pms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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



    @ApiModelProperty(value = "创建者id")
    private Integer createId;



    @ApiModelProperty(value = "创建者名称")
    private String createName;


    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    /**
     *   1正常0禁用
     */
    @ApiModelProperty(value = "1正常0禁用")
    private Boolean statue;


    /**
     *   管理员用户id
     *
     */
    @ApiModelProperty(value = "管理员id")
    private Integer adminId;


    @ApiModelProperty(value = "公司管理员姓名")
    private String adminName;


    @ApiModelProperty(value = "公司菜单")
    @Transient
    private List<Menu>   menus;

    @ApiModelProperty(value = "公司角色")
    @Transient
    private List<Role>    roles;

}