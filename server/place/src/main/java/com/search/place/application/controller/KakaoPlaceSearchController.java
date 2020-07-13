package com.search.place.application.controller;

import com.search.place.application.openapi.OpenApi;
import com.search.place.application.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * OpenApi를 컨트롤러마다 다르게 주입받아서 다른 API가 추가되어도 쉽게 확장 가능
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/kakao")
public class KakaoPlaceSearchController {

    @Autowired
    @Qualifier("kakao")
    private OpenApi openApi;

    @Autowired
    private PlaceSearchService placeSearchService;

    @GetMapping("placeSearch")
    public String placeSearch(@RequestParam("query") String query,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              @RequestParam("longitude") String longitude,
                              @RequestParam("latitude") String latitude) {
        return placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);
    }
}
