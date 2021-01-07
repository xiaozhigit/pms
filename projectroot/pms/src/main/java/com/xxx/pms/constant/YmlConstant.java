package com.xxx.pms.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class YmlConstant {

    // 默认密码
    public static String PASSWORD = "123456";

    // 文件地址映射路径
    public static String IMG_PATH = "/static/";

    @Value("${image.companyLogoAccessPath}")
    private String companyLogoAccessPath;

    @Value("${image.companyLogoLocationPath}")
    private String companyLogoLocationPath;

    @Value("${image.userLogoLocationPath}")
    private String userLogoLocationPath;

    @Value("${image.userLogoAccessPath}")
    private String userLogoAccessPath;

    @Value("${file.userFileAccessPath}")
    private String userFileAccessPath;

    @Value("${file.userFileLocationPath}")
    private String userFileLocationPath;



}
