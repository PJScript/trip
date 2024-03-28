package com.traveloper.tourfinder.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.traveloper.tourfinder.api.KTO.dto.KTOKeywordSearchDto;
import com.traveloper.tourfinder.api.KTO.dto.detail.DetailsCommonDto;
import com.traveloper.tourfinder.api.KTO.dto.detail.DetailsItemDto;
import com.traveloper.tourfinder.api.KTO.service.KTOApiService;
import com.traveloper.tourfinder.course.dto.PlaceDto;
import com.traveloper.tourfinder.course.entity.Place;
import com.traveloper.tourfinder.course.service.PlaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Place", description = "여행지와 관련된 API")
@RestController
@RequestMapping("api/v1/places")
@RequiredArgsConstructor
public class PlaceController {
    private final KTOApiService ktoApiService;
    private final PlaceService placeService;

    // 관광정보 서비스 API 여행지 정보 검색
    @GetMapping("/search")
    public KTOKeywordSearchDto searchPlaces(
            @RequestParam("keyword")
            String keyword,
            @RequestParam("pageNo")
            Integer pageNo

    ) {
        return ktoApiService.getTripPlaces(keyword, pageNo);
    }

    // 관광정보 서비스 API 여행지 정보 검색
    @GetMapping("/detail")
    public DetailsCommonDto placesDetails(
            @RequestParam("contentId")
            String contentId
    ) {
        return ktoApiService.getPlaceDetails(contentId);
    }

    // 여행지 저장 (코스에 추가를 누를떄마다)
    @GetMapping("/save")
    public PlaceDto savePlaces(
            @RequestParam("contentId")
            String contentId
    ) {
        DetailsItemDto itemDto = ktoApiService
                .getPlaceDetails(contentId)
                .getResponse()
                .getBody()
                .getItems()
                .getItem()
                .get(0); // item 리스트에서 첫번째 원소를 가져와서 사용해야 함.

        return placeService.savePlaces(itemDto);
    }
}
