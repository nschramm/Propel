package com.propel.services;

import com.propel.entity.Browser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by U0011960 on 8/23/16.
 */
public class BrowserRecordLoaderService implements RecordLoaderService {

    private static final Log logger = LogFactory.getLog(BrowserRecordLoaderService.class);

    @Value("${log.browser.file.pattern}")
    private String fileNamePattern;

    @Autowired
    @Qualifier(value = "browserRecordStore")
    RecordStoreService browserRecordStore;

    @Autowired
    @Qualifier(value = "browserRecordParsing")
    RecordParsingService browserRecordParsing;

    public BrowserRecordLoaderService() {
    }

    @Override
    public void process(CommandLine commandLineOptions, List<File> logfiles) {

        for (File log : logfiles) {

            List<Browser> browsers = browserRecordParsing.parse(
                    log, Pattern.compile(this.fileNamePattern,Pattern.CASE_INSENSITIVE));

            if (CollectionUtils.isNotEmpty(browsers)) {

                int save = browserRecordStore.save(browsers);
                logger.info(String.format("parsing %s, records=%s, saved=%s", log.getName(), browsers.size(), save));

            }
        }

    }
}
