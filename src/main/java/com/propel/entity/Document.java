package com.propel.entity;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Date;

/**
 * Created by U0011960 on 8/23/16.
 */
public class Document {

    private String sessionId;
    private Date dateTime;
    private String guid;
    private String title;
    private String type; // DOC, SAVE_DOC, PRINT_DOC, EXPORT_DOC, EMAIL_DOC, FLAG_DOC, SUGGESTED_DOC_TITLE
    private String ods;

    private Document() {
    }

    private Document(Builder builder) {
        this(
                builder.document.sessionId,
                builder.document.dateTime,
                builder.document.guid,
                builder.document.title,
                builder.document.type,
                builder.document.ods
        );
    }

    public Document(
            String sessionId,
            Date dateTime,
            String guid,
            String title,
            String type,
            String ods
    ) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.guid = guid;
        this.title = title;
        this.type = type;
        this.ods = ods;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getOds() {
        return ods;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonGenerationException | JsonMappingException exception) {
        } catch (IOException iox) {
        }
        return StringUtils.EMPTY;
    }

    public static Builder builder() {
        return new Builder();
    }


    /*
    <record>
        <time>20160808.004557565</time>
        <tremorId>i0acc37230000015668afa7b7dbea4198</tremorId>
        <type>DOC</type>
        <appId>20</appId>
        <threadId>c111xjwcpdvap#10405:1470421452209.929</threadId>
        <sessionId>6CF7B6EE7702A11FA4B1E8A626A67215</sessionId>
        <userId>623136</userId>
        <userType>1</userType>
        <docId>ifatcaicmmfaq</docId>
        <docTitle>ICMM Frequently Asked Questions (FAQs), 07-Apr-2016</docTitle>
        <docOds>FATCAREPT</docOds>
    </record>
     */
    public static final class Builder {

        private static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern("yyyyMMdd.HHmmssSSS");

        private final Document document;

        private Builder() {
            this.document = new Document();
        }

        public Document build(org.jsoup.nodes.Document logEntry) {
            Element sessionId = logEntry.select("sessionId").first();
            if (sessionId != null) {
                this.document.sessionId = sessionId.html();
            }
            Element time = logEntry.select("time").first();
            if (time != null) {
                this.document.dateTime = DATE_FMT.parseDateTime(time.html()).toDate();
            }
            Element type = logEntry.select("type").first();
            if (type != null) {
                this.document.type = type.html();
            }
            Element docId = logEntry.select("docId").first();
            if (docId != null) {
                this.document.guid = docId.html();
            }
            Element docTitle = logEntry.select("docTitle").first();
            if (docTitle != null) {
                this.document.title = docTitle.html();
            }
            Element docOds = logEntry.select("docOds").first();
            if (docOds != null) {
                this.document.ods = docOds.html();
            }
            return new Document(this);
        }

    }
}
