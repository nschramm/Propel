package com.propel.services;

import com.propel.entity.Browser;
import com.propel.utils.LogFileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by U0011960 on 8/22/16.
 */
public class BrowserRecordParsingService implements RecordParsingService<Browser> {

    private static final Log logger = LogFactory.getLog(BrowserRecordParsingService.class);

    public BrowserRecordParsingService() {}

    @Override
    public List<Browser> parse(File logfile, Pattern pattern) {
        List<Browser> records = new ArrayList<>();

        List<InputStream> fileInputStreams = LogFileUtils.getFileInputStream(logfile, pattern);
        for (InputStream fileInputStream : fileInputStreams) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {

                String logDate = LogFileUtils.parseBrowseLogDateFromFileName(logfile.getName());
                String readline;

                while ((readline = reader.readLine()) != null) {
                    Browser browser = Browser.builder()
                            .logDate(logDate)
                            .buildFromLogEntry(readline);
                    if (browser != null) {
                        records.add(browser);
                    }
                }

            } catch (IOException exception) {
                logger.info("error reading file " + logfile.getName(), exception);
            }
        }

        return records;
    }

}
