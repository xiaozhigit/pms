package com.xxx.pms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 *   用户收藏的目录
 *
 */
@Data
@Table(name = "sys_user_favorite_menu")
public class UserFavoriteMenu {
    /**
     *
     *   ID
     */
    @Id
    private Integer id;

    /**
     *
     *   用户ID
     */
    private Integer userId;

    /**
     *
     *   菜单ID
     */
    private Integer menuId;

    /**
     *
     *   创建时间
     */
    private Date gmtCreate;

    /**
     *
     *   排序
     */
    private Integer sort;


    @ApiModelProperty(value = "收藏：true,取消收藏：false")
    @Transient
    private Boolean  isFavorite;

    
}