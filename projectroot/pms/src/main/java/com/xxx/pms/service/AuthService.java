package com.xxx.pms.service;


import com.xxx.pms.response.Response;

public interface AuthService {

    Response login(String username, String password);
    Response refreshToken(String token);
}
