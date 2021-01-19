package com.xxx.pms.service.impl;



import com.xxx.pms.constant.YmlConstant;
import com.xxx.pms.entity.SysFile;
import com.xxx.pms.mapper.FileMapper;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.FileService;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;
    @Resource
    private YmlConstant ymlConstant;


    @Override
    public SysFile insert(SysFile sysFile) {
        fileMapper.insertSelective(sysFile);
        return sysFile;
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

    @Override
    public Map<String, Object> uploadFile(MultipartFile file, int userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 200);
        String filename = file.getOriginalFilename();
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        String pathSeparator="/";
        String fileSavePath = userId +pathSeparator+filename;
        String fileBasePath = ymlConstant.getUserFileBasePath();
        File f = new File(fileBasePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        File fc = new File(fileBasePath+pathSeparator+userId);
        if (!fc.exists()) {
            fc.mkdir();
        }
        SysFile sysFile, newFile;
        sysFile = new SysFile();
        sysFile.setName(filename);
        sysFile.setUrl(YmlConstant.IMG_PATH + fileSavePath);
        sysFile.setSize(file.getSize() + "");
        sysFile.setType(suffix);
        newFile = this.insert(sysFile);
        File f1 = new File(fileBasePath +pathSeparator+userId, filename);
        resultMap.put("msg", newFile);
        try {
            file.transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("code", 500);
            resultMap.put("msg", "上传文件出错了");
        }
        return resultMap;
    }
}
