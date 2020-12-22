package com.xxx.vcard.service;


import com.xxx.vcard.response.Response;

public interface AuthService {

    public Response login(String username, String password);
    public Response refreshToken(String token);
}
