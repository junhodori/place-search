"# place-search" 

장소 검색 서비스 (# Place Search)
================================

# 1.개요
## 1.1. 장소 검색 서비스란?
오픈 API를 활용하여 현재 위치로 부터 가까운 장소 검색을 제공

Demo : http://www.test.co.kr

# 2.환경
## 2.1 개발 환경
### 2.1.1 개발도구
    1) Visual Studio Code
    2) IntelliJ
    3) P3X Redis UI
    4) Editplus
    5) Postman

### 2.1.2 개발언어 및 기술
    1) Java 8.x
    2) Spring Boot 2.3
    3) Spring Security
    4) Gradle Project
    5) Redis
    6) JPA
    7) Docker
    8) VueJS
    9) JWT

## 2.2 실행환경
### 2.2.1 Client
package.json 파일에 있는 모듈을 npm으로 설치 후 노드 서버 실행

    cd client
    npm install
    npm run serve

Client URL 접속

    http://localhost:8081

아래 계정 3개 중 하나로 로그인

    1) USERNAME : muzi
    2) USERNAME : appeach
    3) USERNAME : ryan
    
    ** PASSWORD : 12345

### 2.2.2 Server
Refresh Gradle Dependencise 실행

    PlaceApplication.java 실행

### 2.2.3 Database
Docker 설치 되어 있는 환경 (기본 설정)

    docker pull redis
    docker run --name redis-container -p 6379:6379 redis

Docker 없는 환경 -> Embedded Redis를 대신 사용해야 함

    application.yml 파일 설정 변경    

    spring:
        profiles:
            active: local
            ... (생략) ...
    ---
    spring:
        profiles: dev
        ... (생략) ...
    

# 3. 기능
## 3.1 로그인
    1) 서버 실행시 기본 유저 계정 3개 생성
    2) 로그인 성공시 AccessToken을 Header에 응답
    3) 로그인 성공시 Index 화면으로 전환
    4) 허용되지 않은 URL 접근 방지 및 뒤로가기 제공
    5) 허용되지 않은 AccessToken일 경우 서버 통신 거부
    6) 로그아웃을 통해 AccessToken 정상 해제

## 3.2 장소검색
    1) 장소 검색 키워드를 통해 기준점으로 부터 가까운 위치 검색
    2) 검색 결과 페이징 제공

## 3.3 장소 상세 조회
    1) 카카오맵 장소 바로가기 제공

## 3.4 인기검색어
    1) 인기검색어로 TOP 10개 표시
    2) 인기검색어 실시간 반영
    3) 인기검색어 카운트

# 4. 외부 라이브러리
## 3.1. Server
    'org.springframework.boot:spring-boot-starter-data-redis' -> Redis
    'org.springframework.boot:spring-boot-starter-data-jpa' -> JPA
    'org.springframework.boot:spring-boot-starter-jersey' -> REST API
    'org.springframework.boot:spring-boot-starter-web' -> 웹 개발
    'org.springframework.boot:spring-boot-starter-security' -> 로그인
    'com.auth0', name: 'java-jwt', version: '3.1.0' -> JWT
    'it.ozimov', name: 'embedded-redis', version: '0.7.2' -> Redis가 없는 환경에서 embedded redis 사용

    com.google.code.gson : Json
    org.projectlombok:lombok : Getter/Setter

    'org.junit.jupiter:junit-jupiter-api:5.3.1' : Junit5 테스트


## 3.2 Client
    vue-axios -> 비동기 통신
    vue-router -> 라우터 사용
    vuetify -> UI 부트스트랩
    vuex -> store 사용
    vuex-persistedstate -> 화면 새로고침시 store 증발 방지


# 4. 참고 자료
Spring Security

Spring Security & JWT :
https://velog.io/@minholee_93/Spring-Security-JWT-Security-Spring-Boot-10

Spring Security & Refresh JWT :
https://velog.io/@tlatldms/Spring-boot-Spring-security-JWT-Redis-mySQL-2%ED%8E%B8

Spring Boot Bean :
https://cbw1030.tistory.com/54

Spring Transactional :
https://goddaehee.tistory.com/167

Vuex & Vue-router :
http://jeonghwan-kim.github.io/2018/03/26/vue-authentication.html

Redis & SpringBoot:
https://jojoldu.tistory.com/297

Vuex store 초기화 문제 :
http://blog.knowgari.com/state%EC%B4%88%EA%B8%B0%ED%99%94%EB%A7%89%EA%B8%B0/

Redis Cluster :
https://rastalion.me/redis-8-docker%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-redis-cluster-%EA%B5%AC%EC%B6%95-ver-5-0-5-buster/

CORS (Corss-Origin Resource Sharing) :
https://icarus8050.tistory.com/28

CSRF (Cross Site Request Forgery) : 
https://itstory.tk/entry/CSRF-%EA%B3%B5%EA%B2%A9%EC%9D%B4%EB%9E%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-CSRF-%EB%B0%A9%EC%96%B4-%EB%B0%A9%EB%B2%95

Docker
참고사이트 : https://gofnrk.tistory.com/84
https://velog.io/@wlsdud2194/-Docker-%EB%8F%84%EC%BB%A4-%EA%B8%B0%EB%B3%B8-%EB%AA%85%EB%A0%B9%EC%96%B4-%EB%AA%A8%EC%9D%8C