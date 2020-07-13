package com.search.place.config;

import com.search.place.application.openapi.KakaoAPI;
import com.search.place.application.openapi.OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Opean API 설정
 *  - Kakao API 추가됨 (2020.07.14)
 */
@Configuration
public class OpenApiConfig {
    @Bean(name="kakao")
    public OpenApi kakaoApi() {
        return new KakaoAPI();
    }
}
