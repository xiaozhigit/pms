package com.xxx.pms.service;


import com.xxx.pms.response.Response;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    Response login(String username, String password);
    Response refreshToken(HttpServletRequest request);
}
