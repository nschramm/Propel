package com.propel.api.browser;

import com.propel.model.BrowserCount;
import com.propel.services.BrowserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by U0011960 on 8/31/16.
 */
@RestController
public class StatisticsController {

    @Autowired
    BrowserStatsService statsService;

    @RequestMapping(path="/browsers",method = RequestMethod.GET)
    public BrowserCount[] browserCounts() {
        List<BrowserCount> allBrowserCount = statsService.getGroupByBrowser();
        return allBrowserCount.toArray(new BrowserCount[allBrowserCount.size()]);
    }

}
