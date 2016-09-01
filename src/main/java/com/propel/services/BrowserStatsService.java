package com.propel.services;

import com.propel.entity.Browser;
import com.propel.model.BrowserCount;
import com.propel.repositories.BrowserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by U0011960 on 8/31/16.
 */
public class BrowserStatsService {

    @Autowired
    BrowserRepository browserRepository;

    public BrowserStatsService() {}

    public Collection<BrowserCount> getAllBrowserCount() {
        Map<String, BrowserCount> browserCountMap = new HashMap<>();
        List<Browser> all = browserRepository.findAll();
        for (Browser browser : all) {
            BrowserCount browserCount = browserCountMap.get(browser.getBrowser());
            if (browserCount == null) {
                browserCount = new BrowserCount(browser.getBrowser(), 0);
                browserCountMap.put(browserCount.getName(), browserCount);
            }
            browserCount.increment();
        }
        return browserCountMap.values();
    }

}
