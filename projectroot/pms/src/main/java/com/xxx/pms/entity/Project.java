package com.xxx.pms.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "公司id", name = "companyId", dataType = "Integer")
    private Integer companyId;

    @ApiModelProperty(value = "项目名称", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(value = "项目名称首字母", name = "initials", dataType = "String")
    private String initials;

    @ApiModelProperty(value = "创建人ID", name = "createUserId", dataType = "Integer")
    private Integer createUserId;

    @ApiModelProperty(value = "创建人昵称", name = "createUserName", dataType = "String")
    private String createUserName;

    @ApiModelProperty(value = "项目描述", name = "describe", dataType = "String")
    private String describes;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间", name = "gmtCreate", dataType = "Date")
    private Date gmtCreate;

    @ApiModelProperty(value = "1删除0正常", name = "delFlag", dataType = "Boolean")
    private Boolean delFlag;

    @ApiModelProperty(value = "项目绑定的人员列表", name = "projectUserList", dataType = "List")
    private List<ProjectUser> projectUserList;

}
