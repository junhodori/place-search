package com.search.place.application;

import com.search.place.application.service.PlaceSearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlaceSearchApplicationTests {

    @Autowired
    private PlaceSearchServiceImpl placeSearchService;

    @Test
    public void placeSearch() {
        String query = "카카오프렌즈";
        Integer page = 1;
        placeSearchService.placeSearch(query, page);
    }
}
