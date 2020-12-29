package com.xxx.pms.controller;

import com.xxx.pms.entity.Menu;
import com.xxx.pms.response.Response;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags={"文件管理"})
@RestController
@RequestMapping("/file")
public class FileController {


    @ApiOperation(value = "文件上传", notes="文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="menu",value="菜单实体类",dataType="Menu",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("upload")
    public Response fileUpload(MultipartFile menu){
        //int  result= menuService.add(menu);
        return  ResponseUtils.success();
    }
}
