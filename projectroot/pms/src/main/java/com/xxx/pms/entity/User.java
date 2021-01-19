package com.xxx.pms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *   公司ID
     */
    private Integer companyId;


    @ApiModelProperty(value = "项目Id")
    private Integer projectId;

    @ApiModelProperty(value = "昵称/姓名", name = "name", dataType = "String")
    private String name;


    @ApiModelProperty(value = "昵称/姓名首字母", name = "initials", dataType = "String")
    private String initials;


    /**
     *   手机号
     */
    private String phone;

    @ApiModelProperty(value = "用户名", name = "username", dataType = "String")
    private String username;

    /**
     *   密码
     */
    private String password;

    /**
     *   角色ID
     */
    private Integer roleId;

    /**
     *   创建人ID
     */
    private Integer createId;

    @ApiModelProperty(value = "创建人昵称", name = "createName", dataType = "String")
    private String createName;

    @ApiModelProperty(value = "头像", name = "image", dataType = "String")
    private String image;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间", name = "gmtCreate", dataType = "Date")
    private Date gmtCreate;

    @ApiModelProperty(value = "用户状态（true:正常,false:禁用）", name = "statue", dataType = "Boolean")
    private Boolean statue;

    /**
     *   0删除1正常
     */
    private Boolean delFlag;

    @Transient
    private String roleName;


    @ApiModelProperty(value="项目名称")
    @Transient
    private String projectName;


}
