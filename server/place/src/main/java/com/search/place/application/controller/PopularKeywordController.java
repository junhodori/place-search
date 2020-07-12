package com.search.place.application.controller;

import com.search.place.application.model.PopularKeyword;
import com.search.place.application.service.PopularKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api")
public class PopularKeywordController {

    @Autowired
    private PopularKeywordService popularKeywordService;

    @GetMapping("popularKeyword")
    public List<PopularKeyword> popularKeyword() {
        return popularKeywordService.selectPopularKeyword();
    }
}
