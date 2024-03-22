package com.traveloper.tourfinder.api.KTO.service;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KTOApiService {
    private final KTOSearchService ktoSearchService;

    // 일반 인증키 (Encoding)
    @Value("${kto.serviceKey}")
    private String serviceKey;

    // 여행지 키워드 검색
    public Object getTripPlaces(
            String keyword
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("numOfRows", 12);
        params.put("pageNo", 1);
        params.put("MobileOS", "ETC");
        params.put("MobileApp", "AppTest");
        params.put("_type", "json");
        params.put("serviceKey", serviceKey);
        params.put("listYN", "Y");
        params.put("arrange", "A");
        params.put("keyword", keyword);
        return ktoSearchService.SearchKeyword(params);
    }

    // 여행지 상세 정보 확인
    public Object getPlaceDetails(
            String contentId
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("serviceKey", serviceKey);
        params.put("MobileOS", "ETC");
        params.put("MobileApp", "AppTest");
        params.put("_type", "json");
        params.put("contentId", contentId);
        params.put("defaultYN", "Y");
        params.put("firstImageYN", "Y");
        params.put("mapinfoYN", "Y");
        params.put("overviewYN", "Y");
        return ktoSearchService.DetailCommon(params);
    }
}
