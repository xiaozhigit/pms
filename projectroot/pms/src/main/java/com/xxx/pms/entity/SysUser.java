package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_user")
public class SysUser {
    @Id
    private String id;
    private String companyId;
    private String name;
    private String phone;
    private String username;
    private String password;
    private String roleId;
    private String createId;
    private String image;
    private Date gmtCreate;
    private Date gmtModified;
    private short statue;
    private boolean delFlag;

}
