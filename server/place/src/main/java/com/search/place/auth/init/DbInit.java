package com.search.place.auth.init;

import com.search.place.application.model.User;
import com.search.place.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DbInit implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Delete all
        this.userRepository.deleteAll();

        // create users
        User first = new User("ryan", passwordEncoder.encode("12345"),"USER","");
        User second = new User("muzi", passwordEncoder.encode("12345"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User third = new User("appeach", passwordEncoder.encode("12345"),"MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(first, second, third);

        // save to db
        this.userRepository.saveAll(users);
    }
}