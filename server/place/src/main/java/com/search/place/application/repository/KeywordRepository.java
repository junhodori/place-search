package com.search.place.application.repository;

import com.search.place.application.model.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PopularKeywordRepository extends CrudRepository<Keyword, String> {
    List<Keyword> findTop1ByOrderByNum();
    List<Keyword> findTop2ByOrderByNum();
    List<Keyword> findTop3ByOrderByNum();
    List<Keyword> findTop4ByOrderByNum();
    List<Keyword> findTop5ByOrderByNum();
    List<Keyword> findTop6ByOrderByNum();
    List<Keyword> findTop7ByOrderByNum();
    List<Keyword> findTop8ByOrderByNum();
    List<Keyword> findTop9ByOrderByNum();
    List<Keyword> findTop10ByOrderByNum();
}
