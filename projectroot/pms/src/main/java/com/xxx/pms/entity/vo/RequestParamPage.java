package com.xxx.pms.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RequestParamPage<T> {

    @ApiModelProperty(value="当前页",name="page",example="1")
    private Integer page = 1;      //默认第一页

    @ApiModelProperty(value="每页显示数据数",name="pageSize",example="20")
    private Integer pageSize = 20;    //默认页面大小20

    @ApiModelProperty(value="参数实体",name="param",example="{}")
    private T param;               //分页条件参数
}
