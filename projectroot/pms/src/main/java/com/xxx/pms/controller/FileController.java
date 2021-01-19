package com.xxx.pms.controller;


import com.xxx.pms.entity.SysFile;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.FileService;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.xxx.pms.constant.AccessStateCodeConstant.FILE_EMPTY;
import static com.xxx.pms.constant.AccessStateCodeConstant.FILE_UPLOAD_FAIL;

/**
 * (SysFile)控制层
 */
@Api(tags={"文件上传"})
@RestController
public class FileController {

    /**
     * 服务对象
     */
    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "单文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "userId" , value = "用户id" , dataType = "int", required=true)
    })
    public Response handleFileUpload(@RequestParam("file") MultipartFile file, int userId) {
        if (!file.isEmpty()) {
            Map<String, Object> result = fileService.uploadFile(file, userId);
            if ((int) result.get("code") == 200) {
                return ResponseUtils.successData(result.get("msg"));
            }
            return ResponseUtils.fillState(FILE_UPLOAD_FAIL);
        }
        return ResponseUtils.fillState(FILE_EMPTY);
    }


    @PostMapping("/txUpload")
    @ApiOperation(value = "头像上传")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "userId" , value = "用户id" , dataType = "int", required=true)
    })
    public Response txUpload(@RequestParam("file") MultipartFile file, int userId) {
        if (file.isEmpty()) {
            return ResponseUtils.fillState(FILE_EMPTY);
        }
        Map<String, Object> result = fileService.uploadFile(file, userId);
        if ((int) result.get("code") == 200) {
            return ResponseUtils.successData(result.get("msg"));
        }
        return ResponseUtils.fillState(FILE_UPLOAD_FAIL);
    }


    @PostMapping(value = "/batchUpload")
    @ApiOperation(value = "批量文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "userId" , value = "用户id" , dataType = "int", required=true)
    })
    public Response batchUpload(@RequestParam(value = "file") MultipartFile[] files, int userId) {
        try {
            List<SysFile> list = new ArrayList<>();
            if (files.length != 0) {
                for (MultipartFile file : files) {
                    if (file.isEmpty()) {
                        return ResponseUtils.fillState(FILE_EMPTY);
                    }
                    Map<String, Object> result = fileService.uploadFile(file, userId);
                    if ((int) result.get("code") == 500) {
                        return ResponseUtils.fillState(FILE_UPLOAD_FAIL);
                    }
                    list.add((SysFile) result.get("msg"));
                }
                return ResponseUtils.successData(list);
            }
            return ResponseUtils.fillState(FILE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.fillState(FILE_UPLOAD_FAIL);
        }
    }



    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "文件id" ,required = true , dataType = "string")
    })
    @DeleteMapping("deleteFile")
    public Response deleteFile(String id) {
        return fileService.deleteFile(id);
    }



    @ApiOperation(value = "根据id查找文件", notes = "根据id查找文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "文件id" ,required = true , dataType = "string")
    })
    @GetMapping("queryById")
    public Response queryById(String id) {
        return fileService.queryById(id);
    }

}