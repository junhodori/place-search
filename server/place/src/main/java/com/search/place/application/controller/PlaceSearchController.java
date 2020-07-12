package com.search.place.application.controller;

import com.search.place.application.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api")
public class PlaceSearchController {

    @Autowired
    private PlaceSearchService placeSearchService;

    @GetMapping("placeSearch")
    public String placeSearch(@RequestParam("query") String query) {
        return placeSearchService.placeSearch(query);
    }
}
