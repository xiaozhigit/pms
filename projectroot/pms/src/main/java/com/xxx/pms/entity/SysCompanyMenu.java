package com.xxx.pms.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *   公司对应的菜单表
 */
@Data
@Table(name = "sys_company_menu")
public class SysCompanyMenu {
    /**
     *   ID
     *
     * @mbg.generated
     */
    @Id
    private Integer id;

    /**
     *   公司ID
     *
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     *   目录ID
     *
     */
    private Integer menuId;


}