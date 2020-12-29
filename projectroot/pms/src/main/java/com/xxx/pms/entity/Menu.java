package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 *   菜单表
 *
 */

@Data
@Table(name = "sys_menu")
public class Menu {
    /**
     *   ID
     */
    @Id
    private Integer id;

    /**
     *   目录名称
     */
    private String name;

    /**
     *   父目录ID
     */
    private Integer parentId;

    /**
     *   目录跳转的路径
     */
    private String url;

    /**
     *   图标
     */
    private String icon;

    /**
     *   同级别的顺序，从0开始
     */
    private Integer sort;

    /**
     *   描述
     */
    private String description;

    /**
     *   创建人ID
     */
    private Integer createId;

    /**
     *   创建人昵称
     */
    private String createName;

    /**
     *   创建时间
     */
    private Date gmtCreate;
    /**
     * 是否收藏
     */
    @Transient
    private Boolean isFavorite;

    @Transient
    private String  companyName;


}