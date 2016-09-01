package com.propel;

import com.propel.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by U0011960 on 8/22/16.
 */
@Configuration
public class ApplicationBeanConfigurer {

    public @Bean
    RecordLoaderService browserRecordLoader() {
        return new BrowserRecordLoaderService();
    }

    public @Bean
    RecordParsingService browserRecordParsing() {
        return new BrowserRecordParsingService();
    }

    public @Bean
    RecordStoreService browserRecordStore() {
        return new BrowserRecordStoreService();
    }

    public @Bean
    RecordLoaderService userActionRecordLoader() {
        return new UserActionRecordLoaderService();
    }

    public @Bean
    RecordParsingService userActionRecordParsing() {
        return new UserActionRecordParsingService();
    }

    public @Bean
    RecordStoreService userActionRecordStore() {
        return new UserActionRecordStoreService();
    }

    public @Bean
    BrowserStatsService browserStatsService() {
        return new BrowserStatsService();
    }

}
