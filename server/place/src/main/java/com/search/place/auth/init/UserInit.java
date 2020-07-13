package com.search.place.auth.init;

import com.search.place.application.model.User;
import com.search.place.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 애플리케이션 실행시점에 사용자 데이터 생성
 *  - muzi (PW : 12345 / Role : ADMIN)
 *  - appeach (PW : 12345 / Role : MANAGER)
 *  - ryan (PW : 12345 / Role : USER)
 */
@RequiredArgsConstructor
@Service
public class UserInit implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();

        User first = new User("muzi", passwordEncoder.encode("12345"),"ADMIN","");
        User second = new User("appeach", passwordEncoder.encode("12345"),"MANAGER","");
        User third = new User("ryan", passwordEncoder.encode("12345"),"USER","");

        List<User> users = Arrays.asList(first, second, third);

        this.userRepository.saveAll(users);
    }
}