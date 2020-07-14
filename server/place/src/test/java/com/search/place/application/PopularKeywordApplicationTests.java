package com.search.place.application;

import com.search.place.application.model.Keyword;
import com.search.place.application.model.PopularKeyword;
import com.search.place.application.repository.KeywordRepository;
import com.search.place.application.repository.PopularKeywordRepository;
import com.search.place.application.service.PopularKeywordService;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PopularKeywordApplicationTests {

    @Autowired
    private PopularKeywordService popularKeywordService;

    @Autowired
    private PopularKeywordRepository popularKeywordRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @BeforeEach
    public void clean() {
        keywordRepository.deleteAll();
        popularKeywordRepository.deleteAll();

        for (int index = 0; index < 10; index++) {
            this.popularKeywordRepository.save(PopularKeyword.builder().id(index).build());
        }
    }

    /**
     * 동일한 키워드 순위의 경우 마지막에 입력된 키워드가 상위 순위
     */
    @Test
    public void placeSearch() {
        Map<String, Integer> place = new HashMap<>();
        place.put("수지파크푸르지오", 150);
        place.put("헬스장", 99);
        place.put("영화관", 30);
        place.put("학원", 30);
        place.put("맛집", 15);
        place.put("병원", 5);

        for (String placeName : place.keySet()) {
            for (int loop = 0; loop < place.get(placeName); loop++) {
                popularKeywordService.insertPopularKeyword(placeName);
            }
        }

        List<PopularKeyword> popularKeywordList = popularKeywordService.selectPopularKeyword();
        assertThat(popularKeywordList.get(0).getKeyword()).isEqualTo("수지파크푸르지오");
        assertThat(popularKeywordList.get(0).getCount()).isEqualTo(place.get("수지파크푸르지오"));

        assertThat(popularKeywordList.get(1).getKeyword()).isEqualTo("헬스장");
        assertThat(popularKeywordList.get(1).getCount()).isEqualTo(place.get("헬스장"));

        assertThat(popularKeywordList.get(2).getKeyword()).isEqualTo("학원");
        assertThat(popularKeywordList.get(2).getCount()).isEqualTo(place.get("학원"));

        assertThat(popularKeywordList.get(3).getKeyword()).isEqualTo("영화관");
        assertThat(popularKeywordList.get(3).getCount()).isEqualTo(place.get("영화관"));

        assertThat(popularKeywordList.get(4).getKeyword()).isEqualTo("맛집");
        assertThat(popularKeywordList.get(4).getCount()).isEqualTo(place.get("맛집"));

        assertThat(popularKeywordList.get(5).getKeyword()).isEqualTo("병원");
        assertThat(popularKeywordList.get(5).getCount()).isEqualTo(place.get("병원"));
    }
}
