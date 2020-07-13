package com.search.place.application.service;

import com.search.place.application.openapi.OpenApi;

public interface PlaceSearchService {

    String placeSearch(OpenApi openApi, String query, Integer page, Integer size, String longitude, String latitude);
}
