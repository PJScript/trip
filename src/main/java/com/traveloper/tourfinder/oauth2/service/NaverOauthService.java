package com.traveloper.tourfinder.oauth2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traveloper.tourfinder.auth.dto.MemberDto;
import com.traveloper.tourfinder.auth.entity.Member;
import com.traveloper.tourfinder.auth.repo.MemberRepository;
import com.traveloper.tourfinder.common.RedisRepo;
import com.traveloper.tourfinder.common.exception.CustomGlobalErrorCode;
import com.traveloper.tourfinder.common.exception.GlobalExceptionHandler;
import com.traveloper.tourfinder.oauth2.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverOauthService {
    private final SocialOauthService socialOauthService;
    private final MemberRepository memberRepository;
    private final String SOCIAL_PROVIDER_NAME = "NAVER";


    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String authorizationUri;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String naverTokenUri;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String naverUserInfoUri;

    public String getNaverLoginUrl(){
        String path = authorizationUri + "?response_type=code" + "&client_id=" + clientId + "&redirect_uri=" + redirectUri;
        return path;
    }

    public String naverLogin(String code){
        NaverTokenResponse tokenResponse = socialOauthService.getSocialProviderAccessTokenRequest(
                SocialProviderAccessTokenRequestDto.builder()
                        .code(code)
                        .socialTokenUri(naverTokenUri)
                        .grantType("authorization_code")
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(redirectUri)
                        .build(),
                SOCIAL_PROVIDER_NAME,
                NaverTokenResponse.class
        );
        NaverUserProfile userInfo = socialOauthService.getProfileRequest(tokenResponse.getAccess_token(),naverUserInfoUri,NaverUserProfile.class);
        String email = userInfo.getResponse().getEmail();
        String nickname = userInfo.getResponse().getEmail() + "_naver";

        Optional<Member> memberOpt = memberRepository.findMemberByEmail(email);
        if (memberOpt.isEmpty()) {
            // 사용자가 존재하지 않으면 회원가입 및 연동 후 토큰 전달
            MemberDto memberDto = socialOauthService.handleNewUser(SOCIAL_PROVIDER_NAME,nickname, email);
            return socialOauthService.getRedirectPathAndSaveOauth2AuthorizeToken(SOCIAL_PROVIDER_NAME, memberDto);
        } else {
            System.out.printf(memberOpt.get().getUuid() + "네이버 UUID");

            // 존재하는 사용자라면 연동 처리
            MemberDto memberDto = socialOauthService.handleExistingUser(SOCIAL_PROVIDER_NAME,memberOpt.get());
            return socialOauthService.getRedirectPathAndSaveOauth2AuthorizeToken(SOCIAL_PROVIDER_NAME, memberDto);
        }
    }
}
