package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_role")
public class SysRole {
    /**
     
     *   ID
     
     */
    @Id
    private Integer id;

    /**
     
     *   公司ID
     
     */
    private Integer companyId;

    /**
     
     *   角色名称
     
     */
    private String name;

    /**
     
     *   角色描述
     
     */
    private String description;

    /**
     
     *   创建人ID
     
     */
    private Integer createId;

    /**
     
     *   创建时间
     
     */
    private Date gmtCreate;
    
}