package com.search.place.auth.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.place.application.model.Login;
import com.search.place.application.model.UserPrincipal;
import com.search.place.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // POST {"username": "", "password": ""} 가져와서 로그인 모델에 매핑
        Login credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), Login.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Spring Security 내부에서 사용하는 Token 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>()
        );

        // 인증처리
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return auth;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        // JSON 웹토큰 생성
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        // 헤더 정보에 JSON 웹토큰 추가
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}