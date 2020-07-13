package com.search.place.application.repository;

import com.search.place.application.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

    List<User> findByPassword(String num);

    User findFirstByUsername(String username);

}