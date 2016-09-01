package com.propel.services;

import com.google.common.collect.Lists;
import com.propel.entity.Browser;
import com.propel.model.BrowserCount;
import com.propel.repositories.BrowserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;


import java.util.*;

/**
 * Created by U0011960 on 8/31/16.
 */
public class BrowserStatsService {

    @Autowired
    MongoTemplate operations;

    @Autowired
    BrowserRepository browserRepository;

    public BrowserStatsService() {}

    public List<BrowserCount> getAllBrowserCount() {
        Map<String, BrowserCount> browserCountMap = new HashMap<>();
        List<Browser> all = browserRepository.findAll();
        for (Browser browser : all) {
            BrowserCount browserCount = browserCountMap.get(browser.getBrowser());
            if (browserCount == null) {
                browserCount = new BrowserCount(browser.getBrowser(), 0);
                browserCountMap.put(browserCount.getBrowser(), browserCount);
            }
            browserCount.increment();
        }
        return Lists.newArrayList(browserCountMap.values());
    }

    public List<BrowserCount> getGroupByBrowser() {

        GroupByResults<BrowserCount> browserCountGroupByResults = operations.group(
                "browsers",
                GroupBy.key("browser")
                        .initialDocument("{count: 0}")
                        .reduceFunction("function(doc, prev) { prev.count += 1 }"),
                BrowserCount.class
        );
        Iterator<BrowserCount> iterator = browserCountGroupByResults.iterator();
        ArrayList<BrowserCount> browserCounts = Lists.newArrayList(iterator);
        browserCounts.forEach(browserCount -> {
            if (StringUtils.isBlank(browserCount.getBrowser())) {
                browserCount.setBrowser("Other(browser field is empty in the log)");
            }
        });
        return browserCounts;
    }

}
