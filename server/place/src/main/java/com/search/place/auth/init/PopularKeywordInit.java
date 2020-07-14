package com.search.place.auth.init;

import com.search.place.application.model.PopularKeyword;
import com.search.place.application.model.User;
import com.search.place.application.repository.PopularKeywordRepository;
import com.search.place.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularKeywordInit implements CommandLineRunner {

    private final PopularKeywordRepository popularKeywordRepository;

    @Override
    public void run(String... args) throws Exception {
        this.popularKeywordRepository.deleteAll();

        for (int index = 0; index < 10; index++) {
            this.popularKeywordRepository.save(PopularKeyword.builder().id(index).build());
        }
    }
}
