package com.search.place.application;

import com.search.place.application.model.Keyword;
import com.search.place.application.model.PopularKeyword;
import com.search.place.application.repository.KeywordRepository;
import com.search.place.application.repository.PopularKeywordRepository;
import com.search.place.application.service.PopularKeywordService;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PopularKeywordApplicationTests {

    @Autowired
    private PopularKeywordService popularKeywordService;

    @Autowired
    private PopularKeywordRepository popularKeywordRepository;

    @Before
    public void clean() {
//        popularKeywordRepository.deleteAll();
    }

    @Test
    public void placeSearch() {

//        popularKeywordService.insertPopularKeyword("수지파크푸르지오");
//        popularKeywordService.insertPopularKeyword("맛집");
//        popularKeywordService.insertPopularKeyword("수지파크푸르지오");
//        popularKeywordService.insertPopularKeyword("풍덕천동");
//        popularKeywordService.insertPopularKeyword("수지파크푸르지오");
//        popularKeywordService.insertPopularKeyword("병원");
//        popularKeywordService.insertPopularKeyword("수지파크푸르지오");
        popularKeywordService.insertPopularKeyword("피시방");

        List<PopularKeyword> popularKeywordList = popularKeywordService.selectPopularKeyword();
        assertThat(popularKeywordList.get(0).getKeyword()).isEqualTo("수지파크푸르지오");
    }
}
