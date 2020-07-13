package com.search.place.auth.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.search.place.application.model.User;
import com.search.place.application.model.UserPrincipal;
import com.search.place.application.repository.UserRepository;
import com.search.place.config.JwtProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 권한 헤더 정보 일기
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // 권한 헤더 정보가 없거나 Bearer 로 시작되지 않는 경우 필터 실행 계속
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // 권한 헤더 정보가 있으면 데이터베이스에서 사용자 정보를 가져와서 권한 부여 수행
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 필터 실행 계속
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);
        if (token != null) {
            // 토큰 구문 분석 및 유효성 검사
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            // DB에서 사용자 정보를 검색하여 권한과 롤을 사용하여 Spring Security 토큰 생성
            if (username != null) {
                User user = userRepository.findById(username).get();
                UserPrincipal principal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
                return auth;
            }

            return null;
        }
        return null;
    }
}