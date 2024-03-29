package com.traveloper.tourfinder.common.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Component
public class PermitAllPath {
    String[] PERMIT_ALL_PATTERN= {
            "/api/v1/auth/sign-in",
            "/api/v1/auth/sign-up",
            "/api-test/**",
            "api/v1/courses",
            "/api/v1/places",

            // html
            "/admin/**",
            "/css/**",
            "/js/**",
            "/*.ico",
            "/*.jpg",
            "/*.jpeg",
            "/*.png",

            "/login","sign-up","/my-page", "/home",
            "/board",
            "/board/articles/create",
            "/swagger-ui/*",
            "/v1/api-docs/*",
            "/v1/api-docs",

            // social
            "/api/v1/oauth2/kakao",
            "/api/v1/oauth2/kakao/callback/**"


    };
}
