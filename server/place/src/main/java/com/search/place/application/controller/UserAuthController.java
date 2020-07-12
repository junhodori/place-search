package com.search.place.application.controller;

import com.search.place.application.model.User;
import com.search.place.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin
public class UserAuthController {

    private final UserRepository userRepository;

    @RequestMapping("/logout")
    public String logout() {
        return "/logout";
    }

    @RequestMapping("/denied")
    public String denied() {
        return "/denied";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    // Available to ROLE_ADMIN
//    @GetMapping("admin/users")
//    public List<User> allUsers(){
//        return this.userRepository.findAll();
//    }
}
