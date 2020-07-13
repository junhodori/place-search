package com.search.place.application;

import com.search.place.application.model.Keyword;
import com.search.place.application.openapi.OpenApi;
import com.search.place.application.repository.KeywordRepository;
import com.search.place.application.service.PlaceSearchServiceImpl;
import com.search.place.application.service.PopularKeywordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlaceSearchApplicationTests {

    @Autowired
    private PlaceSearchServiceImpl placeSearchService;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    @Qualifier("kakao")
    private OpenApi openApi;

    @Test
    public void placeSearch() {
        String query = "카카오프렌즈";
        Integer page = 1;
        Integer size = 10;
        String longitude = "37.5172187";
        String latitude = "127.0411989";

        placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);

        Keyword before = keywordRepository.findById(query).get();

        placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);

        Keyword after = keywordRepository.findById(query).get();

        assertThat(after.getId()).isEqualTo(before.getId());
        assertThat(after.getNum()).isGreaterThan(before.getNum());
    }
}
