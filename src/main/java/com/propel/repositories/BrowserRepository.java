package com.propel.repositories;

import com.propel.entity.Browser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * Created by U0011960 on 8/23/16.
 */
@EnableMongoRepositories
public interface BrowserRepository extends MongoRepository<Browser, String> {

    List<Browser> findAll();
    List<Browser> findByBrowser(String browser);

}
