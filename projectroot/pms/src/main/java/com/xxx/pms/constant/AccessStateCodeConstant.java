package com.xxx.pms.constant;

/**
 * 系统常量
 *
 * @author Liang.Yuan <br>
 *         2016年5月31日
 */
public interface AccessStateCodeConstant {
    /**
     * "0000","访问成功"
     */
    public static final String[] SUCCESS_CODE = {"0000", "操作成功"};
    /**
     * "200","访问成功"
     */
    public static final String[] SUCCESS = {"200", "操作成功"};
    /**
     * "400","操作失败"
     */
    public static final String[] FAIL = {"400", "操作失败"};
    /**
     * "0001", "用户名不存在"
     */
    public static final String[] USERNAME_NOT_FOUND_CODE = {"0001", "用户名不存在"};

    /**
     * "0002", "密码不正确"
     */
    public static final String[] PASSWORD_ERROR = {"0002", "亲，密码不正确"};
    /**
     * "0003", "token不正确"
     */
    public static final String[] TOKEN_ERROR = {"0003", "token 不正确"};

    /**
     * "0004", "您的访问权限不足"
     */
    public static final String[] ACCESS_DENIED = {"0004", "您的访问权限不足"};
    /**
     * "0005","公司名称已存在，请更换"
     */
    public static final String[] COMPANY_REPEAT= {"0005","公司名称已存在，请更换"};
    /**
     * "0006","手机号码已存在，请更换"
     */
    public static final String[] PHONE_NUMBER_REPEAT= {"0006","手机号码已存在，请更换"};
    /**
     * "0007","上传文件不能为空"
     */
    public static final String[] FILE_EMPTY= {"0007","上传文件不能为空"};
    /**
     * "0008","上传文件失败"
     */
    public static final String[] FILE_UPLOAD_FAIL= {"0008","上传文件失败"};

    /**
     * "0007","旧密码输入错误"
     */
    public static final String[] PASSWORD_INFO= {"0007","旧密码输入错误"};

    /**
     * "0008","用户或公司被禁用，无法登录"
     */
    public static final String[] LOGIN_INFO= {"0008","用户或公司被禁用，无法登录"};

    /**
     * "0401", 您还未登录，无权限访问
     */
    public static final String[] AUTH_DENIED = {"0401", "您还未登录，无权限访问"};
    /**
     * "0404","没有发现请求的资源"
     */
    public static final String[] NOT_FOUNT= {"0404","没有发现请求的资源"};
    /**
     * "9999","访问失败"
     */
    public static final String[] FAIL_CODE = {"9999", "访问出错,请联系管理员!"};
}
  
