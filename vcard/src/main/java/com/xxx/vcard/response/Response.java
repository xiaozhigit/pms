package com.xxx.vcard.response;

import lombok.Data;

/**
 * 用来封装返回数据的实体类
 * @param <T>
 */
@Data
public class Response<T> {

    public Response(String[] info, T data) {
        this.code = info[0];
        this.msg = info[1];
        this.data = data;
    }

    private String code;
    private String msg;
    private T data;
}
