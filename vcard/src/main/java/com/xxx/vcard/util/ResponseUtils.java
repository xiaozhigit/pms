package com.xxx.vcard.util;



import com.xxx.vcard.constant.AccessStateCodeConstant;
import com.xxx.vcard.response.Response;
import lombok.Data;

/**
 * @Description: 类功能描述
 * @author: yuan.liang
 * @date: 2019年12月23日 下午2:49:00
 * @Version: V1.0
 */
@Data
public class ResponseUtils {


    /**
     * @Description:访问成功并携带数据返回
     * @param data
     * @throws:异常描述
     * @author: yuan.liang
     * @email: liangyuan@dreamixtech.com
     * @date: 2019年12月23日 下午2:49:18
     */
    public static <T> Response successData(T data) {
        return new Response(AccessStateCodeConstant.SUCCESS_CODE, data);
    }

    /**
     * @Description:访问成功并不携带数据返回
     * @param
     * @throws:异常描述
     * @author: yuan.liang
     * @email: liangyuan@dreamixtech.com
     * @date: 2019年12月23日 下午2:49:18
     * @return
     */
    public static Response success() {
        return new Response(AccessStateCodeConstant.SUCCESS_CODE, null);
    }

    /**
     * @Description: 后台接口出现异常
     * @return
     * @throws:异常描述
     * @author: yuan.liang
     * @email: liangyuan@dreamixtech.com
     * @date: 2019年12月23日 下午2:56:10
     */
    public static Response error() {
        return new Response(AccessStateCodeConstant.FAIL_CODE, null);
    }

    /**
     * @Description: 填充服务器应答消息
     * @param info
     * @return
     * @throws:异常描述
     * @author: yuan.liang
     * @email: liangyuan@dreamixtech.com
     * @date: 2019年12月23日 下午3:03:50
     */
    public static Response fillState(String[] info) {
        return new Response(info, null);
    }

    /**
     * @Description: 填充服务器应答消息和数据
     * @param info
     * @param data
     * @return
     * @throws:异常描述
     * @author: yuan.liang
     * @email: liangyuan@dreamixtech.com
     * @date: 2019年12月23日 下午3:03:28
     */
    public static <T> Response fillStateAndData(String[] info, T data) {
        return new Response(info, data);
    }


}
