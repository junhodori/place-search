package com.search.place.application;

import com.search.place.application.service.PlaceSearchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceSearchLoginApplicationTests {

    @Autowired
    private PlaceSearchServiceImpl placeSearchService;

    @Test
    public void placeSearch() {
        String query = "카카오프렌즈";
        placeSearchService.placeSearch(query);
    }
}
