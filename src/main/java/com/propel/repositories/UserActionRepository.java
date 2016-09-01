package com.propel.repositories;

import com.propel.entity.UserAction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * Created by U0011960 on 8/23/16.
 */
@EnableMongoRepositories
public interface UserActionRepository extends MongoRepository<UserAction, String> {

    @Query("{searches.keywords: { $exists: true } }")
    List<UserAction> findAllWithKeywordSearches();

}
