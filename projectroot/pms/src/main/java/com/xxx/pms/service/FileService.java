package com.xxx.pms.service;


import com.xxx.pms.entity.SysFile;
import com.xxx.pms.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    SysFile insert(SysFile sysFile);

    Response deleteFile(String id);

    Response queryById(String id);

    Map<String,Object> uploadFile(MultipartFile file,int userId);
}
