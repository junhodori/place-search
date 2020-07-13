package com.search.place.application.openapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class KakaoAPI implements OpenApi {
    @Value("${api.kakao.url}")
    private String url;
    @Value("${api.kakao.authorization.prefix}")
    private String authPrefix;
    @Value("${api.kakao.authorization.key}")
    private String authKey;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public HttpEntity<String> getEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, authPrefix + " " + authKey);
        return new HttpEntity<String>(headers);
    }
}
