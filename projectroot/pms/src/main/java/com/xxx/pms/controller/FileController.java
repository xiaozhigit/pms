package com.xxx.pms.controller;


import com.xxx.pms.constant.YmlConstant;
import com.xxx.pms.entity.SysFile;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.FileService;
import com.xxx.pms.util.CommonUtils;
import com.xxx.pms.util.JwtUtils;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysFile)控制层
 *
 * @author makejava
 * @since 2020-12-16 11:02:18
 */
@Api(tags={"文件上传"})
@RestController
public class FileController {

    /**
     * 服务对象
     */
    @Autowired
    private FileService fileService;

    @Value("${file.path}")
    private String filePath;


    @PostMapping("/upload")
    @ResponseBody
    @ApiOperation(value = "单文件上传")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    public Response handleFileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        SysFile sysFile = new SysFile();
        SysFile newFile = null;
        if(!file.isEmpty()){
            try {
                System.out.println(filePath);
                String filename = file.getOriginalFilename();
                String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                String iconname =  JwtUtils.getCompanyIdByRequest(request)+ "/" +filename;

				File f = new File(filePath);
				if (!f.exists()) {
					f.mkdir();
				}
                File fc = new File(filePath+JwtUtils.getCompanyIdByRequest(request));
                if (!fc.exists()) {
                    fc.mkdir();
                }
                sysFile.setId(CommonUtils.getUuid());
                sysFile.setName(filename);
                sysFile.setUrl(YmlConstant.IMG_PATH+iconname);
                sysFile.setType(suffix);
                sysFile.setSize(file.getSize()+"");
                newFile = fileService.insert(sysFile);
                File f1 = new File(filePath + iconname);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f1));
                out.write(file.getBytes());
                out.flush();
                out.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
                return ResponseUtils.errorMessage(new String[]{"500","未发现指定文件"},null);
            }catch (IOException e) {
                e.printStackTrace();
                return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
            }

            return ResponseUtils.successData(newFile);

        }else{
            return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
        }
    }


    @PostMapping("/txUpload")
    @ResponseBody
    @ApiOperation(value = "头像上传")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    public Response txUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){

        SysFile sysFile = new SysFile();
        SysFile newFile = null;
        if(!file.isEmpty()){
            try {
                String filename = file.getOriginalFilename();
                String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                String iconname =  JwtUtils.getCompanyIdByRequest(request)+ "/tx/" +filename;

                File f = new File(filePath);
                if (!f.exists()) {
                    f.mkdir();
                }
                File fc = new File(filePath+JwtUtils.getCompanyIdByRequest(request));
                if (!fc.exists()) {
                    fc.mkdir();
                }
                File fct = new File(filePath+JwtUtils.getCompanyIdByRequest(request)+"/tx");
                if (!fct.exists()) {
                    fct.mkdir();
                }
                sysFile.setId(CommonUtils.getUuid());
                sysFile.setName(filename);
                sysFile.setUrl(YmlConstant.IMG_PATH+iconname);
                sysFile.setSize(file.getSize()+"");
                sysFile.setType(suffix);
                newFile = fileService.insert(sysFile);
                File f1 = new File(filePath  + iconname);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f1));
                out.write(file.getBytes());
                out.flush();
                out.close();
                if (newFile != null) {
                    return ResponseUtils.successData(newFile);
                } else {
                    return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
                }
            }catch(FileNotFoundException e) {
                e.printStackTrace();
                return ResponseUtils.errorMessage(new String[]{"500","未发现指定文件"},null);
            }catch (IOException e) {
                e.printStackTrace();
                return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
            }

        }else{
            return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
        }
    }


    @ResponseBody
    @PostMapping(value = "/batchUpload")
    @ApiOperation(value = "批量文件上传")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    public Response batchUpload(@RequestParam(value = "file") MultipartFile[] file, HttpServletRequest request) {
        try {
            System.out.println("上传数组图片的长度是"+file.length);
            List<SysFile> list = new ArrayList<>();
            String ls= "";
            if (file.length!=0){
                for (int i = 0; i < file.length; i++) {
                    /**
                     * 必须进行判断，否则数组会越界
                     */
                    MultipartFile files = file[i];
                    if (files.isEmpty()){
                        return ResponseUtils.errorMessage(new String[]{"500","未发现指定文件"},null);
                    }
                    //文件原始名字
                    String filename = file[i].getOriginalFilename();
                    String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                    String iconname =  JwtUtils.getCompanyIdByRequest(request)+ "/" +filename;
                    File f = new File(filePath);
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    File fc = new File(filePath+JwtUtils.getCompanyIdByRequest(request));
                    if (!fc.exists()) {
                        fc.mkdir();
                    }
                    SysFile sysFile = new SysFile();
                    sysFile.setId(CommonUtils.getUuid());
                    sysFile.setName(filename);
                    sysFile.setUrl(YmlConstant.IMG_PATH+iconname);
                    sysFile.setSize(file[i].getSize()+"");
                    sysFile.setType(suffix);
                    SysFile newFile = fileService.insert(sysFile);
                    list.add(newFile);
                    File f1 = new File(filePath +iconname);
                    if(!f1.exists()){
                        //先得到文件的上级目录，并创建上级目录，在创建文件
                        f1.getParentFile().mkdir();
                        try {
                            //创建文件
                            f1.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f1));
                    out.write(file[i].getBytes());
                    out.flush();
                    out.close();
                }
                return ResponseUtils.successData(list);
            }
            return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.errorMessage(new String[]{"500","上传失败"},null);
        }
    }


    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "文件id" ,required = true , dataType = "string")
    })
    @DeleteMapping("deleteFile")
    public Response deleteFile(String id) {
        return fileService.deleteFile(id);
    }


    /**
     * 根据id查找文件
     */
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