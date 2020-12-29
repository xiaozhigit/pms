package com.xxx.pms.service;


import com.xxx.pms.entity.SysFile;
import com.xxx.pms.response.Response;

public interface FileService {
    SysFile insert(SysFile sysFile);

    Response deleteFile(String id);

    Response queryById(String id);
}
