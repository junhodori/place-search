package com.search.place.application.openapi;

import org.springframework.http.HttpEntity;

public interface OpenApi {
    String AUTHORIZATION = "Authorization";

    String getUrl();
    HttpEntity<String> getEntity();
}
