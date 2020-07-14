package com.search.place.application.repository;

import com.search.place.application.model.PopularKeyword;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PopularKeywordRepository extends CrudRepository<PopularKeyword, Integer> {
    List<PopularKeyword> findAllByOrderById();
}
