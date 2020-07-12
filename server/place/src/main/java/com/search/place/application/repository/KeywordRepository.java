package com.search.place.application.repository;

import com.search.place.application.model.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, String> {

}
