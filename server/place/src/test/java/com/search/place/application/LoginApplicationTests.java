package com.search.place.application;

import com.search.place.application.model.User;
import com.search.place.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertSelect() {
        String username = "jeju@gmail.com";
        String password = "12345";

        User userAuth = User.builder()
                .username(username)
                .password(password)
                .build();

        userRepository.save(userAuth);

        User selectUserAuth = userRepository.findFirstByUsername(username);

        assertThat(selectUserAuth.getUsername()).isEqualTo(username);
        assertThat(selectUserAuth.getPassword()).isEqualTo(password);
    }
}
