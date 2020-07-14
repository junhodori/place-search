package com.search.place.application;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.search.place.application.model.Keyword;
import com.search.place.application.model.PopularKeyword;
import com.search.place.application.openapi.OpenApi;
import com.search.place.application.repository.KeywordRepository;
import com.search.place.application.service.PlaceSearchServiceImpl;
import com.search.place.application.service.PopularKeywordService;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void clean() {
        keywordRepository.deleteAll();
    }

    /**
     * 동일한 쿼리에 대해서 첫번째와 두번째 실행시 쿼리 카운트 증가 테스트
     */
    @Test
    public void queryCount() {
        String query = "학교";
        Integer page = 1;
        Integer size = 10;
        String longitude = "37.5172187";
        String latitude = "127.0411989";

        placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);
        Keyword before = keywordRepository.findById(query).get();

        placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);
        Keyword after = keywordRepository.findById(query).get();

        assertThat(after.getId()).isEqualTo(before.getId());
        assertThat(after.getCount()).isGreaterThan(before.getCount());
    }

    /**
     * API로 검색한 쿼리문과 동일한 결과물이 나오는지 테스트
     */
    @Test
    public void queryResult() {
        String query = "미용실";
        Integer page = 1;
        Integer size = 10;
        String longitude = "37.5172187";
        String latitude = "127.0411989";

        String result = placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(result);
        JsonObject meta = (JsonObject) jsonObject.get("meta");
        JsonObject same_name = (JsonObject) meta.get("same_name");
        JsonElement keyword = same_name.get("keyword");

        assertThat(keyword.getAsString()).isEqualTo(query);
    }

    /**
     * API로 많은 데이터를 검색시 문제가 없는지
     */
    @Test
    public void queryMany() {
        String query = "야구장";
        Integer page = 1;
        Integer size = 10;
        String longitude = "37.5172187";
        String latitude = "127.0411989";

        int loopCount = 50;

        for (int loop = 0; loop < loopCount; loop++) {
            placeSearchService.placeSearch(openApi, query, page, size, longitude, latitude);
        }

        assertThat(keywordRepository.findById(query).get().getCount()).isEqualTo(loopCount);
    }
}
