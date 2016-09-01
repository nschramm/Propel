package com.propel.entity;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;

import java.util.Date;

/**
 * Created by U0011960 on 8/25/16.
 */
public class News {

    private String sessionId;
    private Date dateTime;
    private String type; // NEWS, PRINT_NEWS, EXPORT_NEWS, EMAIL_NEWS
    private String service;
    private String articledate;

    private News() {}

    private News(Builder builder) {
        this(
                builder.news.sessionId,
                builder.news.dateTime,
                builder.news.type,
                builder.news.service,
                builder.news.articledate
        );
    }

    public News(
            String sessionId,
            Date dateTime,
            String type,
            String service,
            String articledate
    ) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.type = type;
        this.service = service;
        this.articledate = articledate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public String getService() {
        return service;
    }

    public String getArticledate() {
        return articledate;
    }

    public static Builder builder() {
        return new Builder();
    }

    /*
    <record>
        <time>20160623.013856341</time>
        <tremorId>i0ad72f44000001557bfba0ea04c6b415</tremorId>
        <type>NEWS|PRINT_NEWS|EXPORT_NEWS|EMAIL_NEWS</type>
        <appId>20</appId>
        <threadId>c104bdrcppdap#10401:1465993339273.504543</threadId>
        <sessionId>CF79AE18F00F7F9B25F453C99F94829B</sessionId>
        <userId>4212676</userId>
        <userType>9</userType>
        <news>
            <service>News &amp; Insight:Home</service>
        </news>
    </record>
    */

    public static final class Builder {

        private static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern("yyyyMMdd.HHmmssSSS");

        private final News news;

        private Builder() {
            this.news = new News();
        }

        public News build(org.jsoup.nodes.Document logEntry) {
            Element sessionId = logEntry.select("sessionId").first();
            if (sessionId != null) {
                this.news.sessionId = sessionId.html();
            }
            Element time = logEntry.select("time").first();
            if (time != null) {
                this.news.dateTime = DATE_FMT.parseDateTime(time.html()).toDate();
            }
            Element type = logEntry.select("type").first();
            if (type != null) {
                this.news.type = type.html();
            }
            Element service = logEntry.select("service").first();
            if (service != null) {
                this.news.service = service.html();
            }
            Element articleDate = logEntry.select("date").first();
            if (articleDate != null) {
                this.news.articledate = articleDate.html();
            }
            return new News(this);
        }

    }
}
