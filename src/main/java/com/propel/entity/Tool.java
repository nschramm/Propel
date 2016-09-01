package com.propel.entity;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;

import java.util.Date;

/**
 * Created by U0011960 on 8/25/16.
 */
public class Tool {

    private String sessionId;
    private Date dateTime;
    private String type; // TOOL|PRINT_CHART|EXPORT_CHART|EMAIL_CHART|COMBINE_CHART|FLIP_AXIS_CHART
    private String tabId;
    private String toolLabel;
    private String quickLinkLabel;

    private Tool() {}

    private Tool(Builder builder) {
        this(
                builder.tool.sessionId,
                builder.tool.dateTime,
                builder.tool.type,
                builder.tool.tabId,
                builder.tool.toolLabel,
                builder.tool.quickLinkLabel
        );
    }

    public Tool(
            String sessionId,
            Date dateTime,
            String type,
            String tabId,
            String toolLabel,
            String quickLinkLabel
    ) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.type = type;
        this.tabId = tabId;
        this.toolLabel = toolLabel;
        this.quickLinkLabel = quickLinkLabel;
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

    public String getTabId() {
        return tabId;
    }

    public String getToolLabel() {
        return toolLabel;
    }

    public String getQuickLinkLabel() {
        return quickLinkLabel;
    }

    public static Builder builder() {
        return new Builder();
    }

    /*
    <record>
    <time>20160623.080708567</time>
    <tremorId>i0ad72f44000001557d5f0a5604c7600b</tremorId>
    <type>TOOL|PRINT_CHART|EXPORT_CHART|EMAIL_CHART|COMBINE_CHART|FLIP_AXIS_CHART</type>
    <appId>20</appId>
    <threadId>c104bdrcppdap#10401:1465993339273.523875</threadId>
    <sessionId>31760856F935B1D9B097EAEC9C50889D</sessionId>
    <userId>2957072</userId>
    <userType>2</userType>
    <tool>
        <appTabID>4021</appTabID>
        <toolLabel>State &amp; Local</toolLabel>
        <quickLinkLabel>State &amp; Local Create-a-Chart</quickLinkLabel>
    </tool>
    </record>

    private String tabId;
    private String toolLabel;
    private String quickLinkLabel;

    */
    public static final class Builder {

        private static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern("yyyyMMdd.HHmmssSSS");

        private final Tool tool;

        private Builder() {
            this.tool = new Tool();
        }

        public Tool build(org.jsoup.nodes.Document logEntry) {
            Element sessionId = logEntry.select("sessionId").first();
            if (sessionId != null) {
                this.tool.sessionId = sessionId.html();
            }
            Element time = logEntry.select("time").first();
            if (time != null) {
                this.tool.dateTime = DATE_FMT.parseDateTime(time.html()).toDate();
            }
            Element type = logEntry.select("type").first();
            if (type != null) {
                this.tool.type = type.html();
            }
            Element appTabID = logEntry.select("appTabID").first();
            if (appTabID != null) {
                this.tool.tabId = appTabID.html();
            }
            Element toolLabel = logEntry.select("toolLabel").first();
            if (toolLabel != null) {
                this.tool.toolLabel = toolLabel.html();
            }
            Element quickLinkLabel = logEntry.select("quickLinkLabel").first();
            if (quickLinkLabel != null) {
                this.tool.quickLinkLabel = quickLinkLabel.html();
            }
            return new Tool(this);
        }

    }
}
