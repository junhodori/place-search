package com.search.place.application.service;

import com.search.place.application.openapi.OpenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class PlaceSearchServiceImpl implements PlaceSearchService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PopularKeywordService popularKeywordService;

    @Override
    public String placeSearch(OpenApi openApi, String query, Integer page, Integer size, String longitude, String latitude) {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(openApi.getUrl())
                    .queryParam("y", longitude)
                    .queryParam("x", latitude)
                    .queryParam("radius", "20000")
                    .queryParam("sort", "distance")
                    .queryParam("query", query)
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .build(false);

        popularKeywordService.insertPopularKeyword(query);

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, openApi.getEntity(), String.class);
        return response.getBody();
    }
}
