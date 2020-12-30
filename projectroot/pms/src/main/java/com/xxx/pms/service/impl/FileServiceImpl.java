package com.xxx.pms.service.impl;



import com.xxx.pms.entity.SysFile;
import com.xxx.pms.mapper.FileMapper;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.FileService;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;


    @Override
    public SysFile insert(SysFile sysFile) {
        fileMapper.insertSelective(sysFile);
        SysFile newFile = fileMapper.selectByPrimaryKey(sysFile.getId());
        return newFile;
    }

    @Override
    public Response deleteFile(String id) {
        fileMapper.deleteByPrimaryKey(id);
        return ResponseUtils.success();
    }

    @Override
    public Response queryById(String id) {
        SysFile sysFile = fileMapper.selectByPrimaryKey(id);
        return ResponseUtils.successData(sysFile);
    }
}
