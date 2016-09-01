package com.propel.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by U0011960 on 8/22/16.
 */
@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    public MongoConfiguration() {}

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

}
