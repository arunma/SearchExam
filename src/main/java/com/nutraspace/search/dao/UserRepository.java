package com.nutraspace.search.dao;

import com.nutraspace.search.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Arun
 */
public interface UserRepository extends CrudRepository<User, Long> {

    //@Query("SELECT userrating FROM UserRating userrating where url in :urls")
    User findByUsername(String userName);
}
