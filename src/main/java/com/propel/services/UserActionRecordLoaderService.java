package com.propel.services;

import com.propel.entity.UserAction;
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
public class UserActionRecordLoaderService implements RecordLoaderService {

    private static final Log logger = LogFactory.getLog(UserActionRecordLoaderService.class);

    @Value("${log.useraction.file.pattern}")
    private String fileNamePattern;

    @Autowired
    @Qualifier(value = "userActionRecordStore")
    RecordStoreService userActionRecordStore;

    @Autowired
    @Qualifier(value = "userActionRecordParsing")
    RecordParsingService userActionRecordParsing;

    public UserActionRecordLoaderService() {}

    @Override
    public void process(CommandLine commandLineOptions, List<File> logfiles) {

        for (File log : logfiles) {

            List<UserAction> userActions = userActionRecordParsing.parse(
                    log, Pattern.compile(this.fileNamePattern, Pattern.CASE_INSENSITIVE));

            if (CollectionUtils.isNotEmpty(userActions)) {

                int save = userActionRecordStore.save(userActions);
                logger.info(String.format("parsing %s, records=%s, saved=%s", log.getName(), userActions.size(), save));

            }
        }

    }
}
