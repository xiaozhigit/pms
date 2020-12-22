package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_dictionary")
public class SysDictionary {
    /**
    
     *   编号
     */
    @Id
    private String id;

    /**
    
     *   名称
     
     */
    private String name;

    /**
    
     *   父级编号
     
     */
    private String pid;

    /**
    
     *   类型
     
     */
    private String type;

    /**
    
     *   排序
     
     */
    private Integer sortNo;

    /**
    
     *   公司编号
     
     */
    private String companyId;

    /**
    
     *   删除状态 0正常 1已删除
     
     */
    private Integer delFlag;

    /**
    
     *   创建时间
     
     */
    private Date createTime;

    /**
    
     *   创建人
     
     */
    private String createId;

    /**
    
     *   描述
     
     */
    private String description;

    /**
     *   是否为系统内置
     *
     */
    private Integer builtinData;

}