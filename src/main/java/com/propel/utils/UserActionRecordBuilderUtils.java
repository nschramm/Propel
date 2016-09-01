package com.propel.utils;

import com.propel.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import javax.annotation.Nullable;

/**
 * Created by U0011960 on 8/24/16.
 */
public class UserActionRecordBuilderUtils {

    private static final Parser parser = Parser.xmlParser();


    org.jsoup.nodes.Document log;
    private String sessionId;
    private String action;

    public UserActionRecordBuilderUtils(String logEntry) {

        log = Jsoup.parse(logEntry, "", parser);
        log.outputSettings().prettyPrint(false);

        Element sessionId = log.select("sessionId").first();
        if (sessionId != null) {
            this.sessionId = sessionId.html();
        }
        Element type = log.select("type").first();
        if (type != null) {
            this.action = type.html();
        }

    }

    @Nullable
    public UserAction buildUserAction() {

        if (StringUtils.isNotBlank(this.sessionId) &&
                StringUtils.equalsIgnoreCase(this.action, "LOGIN")) {
            return UserAction.builder().build(log);
        }
        return null;
    }

    @Nullable
    public Search buildSearch() {

        if (StringUtils.isNotBlank(this.sessionId) &&
                StringUtils.equalsIgnoreCase(this.action, "SEARCH")) {
            return Search.builder().build(log);
        }
        return null;
    }

    @Nullable
    public Document buildDocument() {

        if (StringUtils.isNotBlank(this.sessionId) &&
                isDocumentAction(this.action)) {
            return Document.builder().build(log);
        }
        return null;
    }

    @Nullable
    public News buildNews() {

        if (StringUtils.isNotBlank(this.sessionId) &&
                isNewsAction(this.action)) {
            return News.builder().build(log);
        }
        return null;
    }

    @Nullable
    public Tool buildTool() {

        if (StringUtils.isNotBlank(this.sessionId) &&
                isToolAction(this.action)) {
            return Tool.builder().build(log);
        }
        return null;
    }

    private boolean isDocumentAction(String type) {
        return StringUtils.equalsIgnoreCase(type, "DOC") ||
                StringUtils.equalsIgnoreCase(type, "PRINT_DOC") ||
                StringUtils.equalsIgnoreCase(type, "EXPORT_DOC") ||
                StringUtils.equalsIgnoreCase(type, "EMAIL_DOC") ||
                StringUtils.equalsIgnoreCase(type, "SAVE_DOC") ||
                StringUtils.equalsIgnoreCase(type, "ADD_DOC_NOTE") ||
                StringUtils.equalsIgnoreCase(type, "FLAG_DOC") ||
                StringUtils.equalsIgnoreCase(type, "SUGGESTED_DOC_TITLE");
    }

    private boolean isNewsAction(String type) {
        return StringUtils.equalsIgnoreCase(type, "NEWS") ||
                StringUtils.equalsIgnoreCase(type, "PRINT_NEWS") ||
                StringUtils.equalsIgnoreCase(type, "EXPORT_NEWS") ||
                StringUtils.equalsIgnoreCase(type, "EMAIL_NEWS");
    }

    private boolean isToolAction(String type) {
        return StringUtils.equalsIgnoreCase(type, "TOOL") ||
                StringUtils.equalsIgnoreCase(type, "PRINT_CHART") ||
                StringUtils.equalsIgnoreCase(type, "EXPORT_CHART") ||
                StringUtils.equalsIgnoreCase(type, "EMAIL_CHART") ||
                StringUtils.equalsIgnoreCase(type, "COMBINE_CHART") ||
                StringUtils.equalsIgnoreCase(type, "FLIP_AXIS_CHART");
    }
}
