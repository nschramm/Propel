package com.propel.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by U0011960 on 8/22/16.
 */
public class LogFileUtils {

    private static final Log logger = LogFactory.getLog(LogFileUtils.class);

    private static final Pattern BROWSER_LOG_DATE_PATTERN = Pattern.compile("\\d*_\\d*_\\d*");
    private static final DateTimeFormatter BROWSER_LOG_DATE_1_FMT = DateTimeFormat.forPattern("yy_MM_dd");
    private static final DateTimeFormatter BROWSER_LOG_DATE_2_FMT = DateTimeFormat.forPattern("yyyy_MM_dd");
    private static final DateTimeFormatter BROWSER_LOG_STD_DATE_FMT = DateTimeFormat.forPattern("MM/dd/yyyy");

    private LogFileUtils() {
    }

    public static List<File> get(String fileSpec) {
        List<File> files = new ArrayList<>();

        if (StringUtils.isNotBlank(fileSpec)) {
            File logPath = new File(fileSpec);

            try {
                Path p = logPath.toPath();
                // check for file pattern
                String filePattern = logPath.isDirectory() ?
                        Paths.get(p.toAbsolutePath().toString(), "*").toAbsolutePath().toString() :
                        p.toAbsolutePath().toString();

                String startPath = logPath.isDirectory() ?
                        p.toAbsolutePath().toString() :
                        p.getParent().toAbsolutePath().toString();

                LogFileFinder finder = new LogFileFinder(filePattern);
                Files.walkFileTree(Paths.get(startPath), finder);

                files.addAll(finder.getMatchedPaths().stream().map(path -> path.toAbsolutePath().toFile()).collect(Collectors.toList()));

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return files;
    }

    public static List<InputStream> getFileInputStream(File file, Pattern pattern) {
        List<InputStream> inputStreams = new ArrayList<>();
        if (StringUtils.endsWithIgnoreCase(file.getName(), "zip")) {

            // get the user action file path from the zip entry
            ZipFile zipFile = null;
            try {
                zipFile = new ZipFile(file);
            } catch (IOException exception) {
                logger.info("error reading file::" + file.getName(), exception);
            }

            if (zipFile != null) {

                List<ZipEntry> userActions = LogFileUtils.getZipFileEntry(zipFile, pattern);
                for (ZipEntry userAction : userActions) {

                    try {
                        inputStreams.add(zipFile.getInputStream(userAction));
                    } catch (IOException exception) {
                        logger.info("error creating inputstream::" + userAction.getName(), exception);
                    }
                }
            }

        } else if (!file.isDirectory() && pattern.matcher(file.getName()).find()) {
            try {
                inputStreams.add(new FileInputStream(file));
            } catch (IOException exception) {
                logger.info("error creating inputstream::" + file.getName(), exception);
            }
        }
        return inputStreams;
    }

    public static List<ZipEntry> getZipFileEntry(ZipFile zipFile, Pattern pattern) {
        List<ZipEntry> zipEntries = new ArrayList<>();

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            if (pattern.matcher(zipEntry.getName()).find()) {
                zipEntries.add(zipEntry);
            }
        }

        return zipEntries;
    }

    public static String parseBrowseLogDateFromFileName(String filename) {

        Matcher matcher = BROWSER_LOG_DATE_PATTERN.matcher(filename);
        if (!matcher.find()) {
            return StringUtils.EMPTY;
        }

        DateTime fileNameDate = getDateTime(matcher.group());
        return fileNameDate != null?
                BROWSER_LOG_STD_DATE_FMT.print(fileNameDate) :
                StringUtils.EMPTY;

    }

    private static DateTime getDateTime(String dateStr) {
        DateTime fileNameDate;
        switch (dateStr.length()) {
            case 8:
                fileNameDate = createDate(BROWSER_LOG_DATE_1_FMT, dateStr);
                break;
            default:
                fileNameDate = createDate(BROWSER_LOG_DATE_2_FMT, dateStr);
                break;
        }
        return fileNameDate;
    }

    private static DateTime createDate(DateTimeFormatter formatter, String dateStr) {
        return formatter.parseDateTime(dateStr);
    }
}
