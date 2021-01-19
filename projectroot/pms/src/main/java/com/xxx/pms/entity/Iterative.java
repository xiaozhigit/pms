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

    @ApiModelProperty(value = "产品原型链接地址", name = "productPrototypeUrl", dataType = "String")
    private String productPrototypeUrl;

    @ApiModelProperty(value = "UI原型图链接地址", name = "uiPrototypeUrl", dataType = "String")
    private String uiPrototypeUrl;

}
