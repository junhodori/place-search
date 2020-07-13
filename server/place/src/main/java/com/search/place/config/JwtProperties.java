package com.search.place.config;

/**
 * Json Web Token 설정
 */
public class JwtProperties {
    public static final String SECRET = "junhodori@gmail.com";
    public static final int EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}