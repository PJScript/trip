package com.traveloper.tourfinder.oauth2.dto;


import lombok.Getter;

@Getter
public class KakaoTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String refresh_token_expires_in;
}
