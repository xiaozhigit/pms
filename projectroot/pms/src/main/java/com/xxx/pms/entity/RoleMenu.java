package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *   角色对应的菜单表
 *
 */
@Data
@Table(name = "sys_role_menu")
public class RoleMenu {
    /**
     *   ID
     */
    @Id
    private Integer id;

    /**
     *   角色ID
     */
    private Integer roleId;

    /**
     *   菜单ID
     */
    private Integer menuId;
    
}