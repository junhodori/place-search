package com.search.place.application.service;


import com.search.place.application.model.Keyword;
import com.search.place.application.model.PopularKeyword;

import java.util.List;
import java.util.Optional;

public interface PopularKeywordService {
    public void insertPopularKeyword(String keyword);
    public Optional<Keyword> selectOnePopularKeyword(Keyword keyword);
    public List<PopularKeyword> selectPopularKeyword();
}

