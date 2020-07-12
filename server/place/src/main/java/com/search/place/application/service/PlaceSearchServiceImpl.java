package com.search.place.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    @Override
    public String placeSearch(String query) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK edec42216b39a35f0a6d0ee51a555f9a");

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        String url = "https://dapi.kakao.com/v2/local/search/keyword.json";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("y", "37.514322572335935")
                    .queryParam("x", "127.06283102249932")
                    .queryParam("radius", "20000")
                    .queryParam("query", query).build(false);

        log.debug(builder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCodeValue() == 200) {
            return response.getBody();
        } else {
            return response.getBody();
        }

    }
}
