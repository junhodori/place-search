"# place-search" 

Spring initializr
Project : Gradle Project
Language : Java 8
Spring Boot : 2.3.1
Packagin : War

Dependencies
1) Spring Web
2) Spring Security
3) Spring Data JPA
4) Jersey


Docker
docker pull redis
docker run --name redis-container -p 6379:6379 redis

개발 환경
P3X Redis UI v2020.10.112
참고사이트 : https://qjadud22.tistory.com/55

Docker
참고사이트 : https://gofnrk.tistory.com/84

IntelliJ
참고사이트

Redis - SpringBoot 연동
참고사이트 : https://jojoldu.tistory.com/297

Lombok
참고사이트 : https://imasoftwareengineer.tistory.com/31

Spring Security
참고사이트 : 
https://sjh836.tistory.com/165
https://victorydntmd.tistory.com/328

JWT
참고사이트 :
https://velog.io/@minholee_93/Spring-Security-JWT-Security-Spring-Boot-10


VueX, Vue-router
참고사이트 : http://jeonghwan-kim.github.io/2018/03/26/vue-authentication.html

Bean 등록
참고사이트 : https://cbw1030.tistory.com/54

VueX 새로고침시 store 문제 해결
참고사이트 : http://blog.knowgari.com/state%EC%B4%88%EA%B8%B0%ED%99%94%EB%A7%89%EA%B8%B0



예외상황
API Server만 내려갔을 경우

Junit 5
Junit 5에서는 헷갈림 방지를 위해 아래와 같이 변경되었습니다.

@BeforeClass -> @BeforeAll

@Before -> @BeforeEach

@After -> @AfterEach

@AfterClass -> @AfterAll