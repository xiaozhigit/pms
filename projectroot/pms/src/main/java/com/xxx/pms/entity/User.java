package com.xxx.pms.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_user")
public class User {
    @Id
    private String id;
    /**
     *   公司ID
     */
    private Integer companyId;

    /**
     *   昵称/姓名
     */
    private String name;

    /**
     *   手机号
     */
    private String phone;

    /**
     *   用户名
     */
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

    /**
     *   头像
     */
    private String image;

    /**
     *   创建时间
     */
    private Date gmtCreate;


    /**
     *   1正常0禁用
     */
    private Boolean statue;

    /**
     *   0删除1正常
     */
    private Boolean delFlag;


}