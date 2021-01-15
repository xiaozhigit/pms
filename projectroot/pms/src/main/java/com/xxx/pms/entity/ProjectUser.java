package com.xxx.pms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "project_user")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "项目ID", name = "projectId", dataType = "Integer")
    private Integer projectId;

    @ApiModelProperty(value = "用户ID", name = "userId", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(value = "用户姓名", name = "userName", dataType = "String")
    private String userName;

}
