package com.xxx.pms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "iterative")
public class Iterative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "项目id", name = "projectId", dataType = "Integer")
    private Integer projectId;

    @ApiModelProperty(value = "迭代名字", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(value = "迭代备注", name = "remarks", dataType = "String")
    private String remarks;

    @ApiModelProperty(value = "创建人ID", name = "createUserId", dataType = "Integer")
    private Integer createUserId;

    @ApiModelProperty(value = "创建人昵称", name = "createUserName", dataType = "String")
    private String createUserName;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间", name = "gmtCreate", dataType = "Date")
    private Date gmtCreate;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "迭代计划开始时间", name = "gmtPlanStart", dataType = "Date")
    private Date gmtPlanStart;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "迭代计划结束时间", name = "gmtPlanEnd", dataType = "Date")
    private Date gmtPlanEnd;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "迭代完成时间", name = "gmtFinish", dataType = "Date")
    private Date gmtFinish;

    @ApiModelProperty(value = "产品原型链接地址", name = "productPrototypeUrl", dataType = "String")
    private String productPrototypeUrl;

    @ApiModelProperty(value = "UI原型图链接地址", name = "uiPrototypeUrl", dataType = "String")
    private String uiPrototypeUrl;

    @ApiModelProperty(value = "迭代状态1完成，0创建", name = "state", dataType = "Integer")
    private Integer state;

}
