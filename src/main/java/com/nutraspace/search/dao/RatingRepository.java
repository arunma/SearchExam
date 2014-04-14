package com.nutraspace.search.dao;

import com.nutraspace.search.model.UserRating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Arun
 */
public interface RatingRepository extends CrudRepository<UserRating, Long> {

    //@Query("SELECT userrating FROM UserRating userrating where url in :urls")
    List<UserRating> findByUrlIn(List<String> urlsList);
}
