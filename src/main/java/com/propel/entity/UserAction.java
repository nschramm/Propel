package com.propel.entity;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;
import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by U0011960 on 8/23/16.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "useractions")
public class UserAction {
    @Id
    private String sessionId;
    private Date dateTime;
    private String appId;
    private String threadId;
    private String userId;
    private String userName;
    private String userType;
    private String accountId;
    private String customerNumber;
    private String groupId;
    private String ipAddress;
    private boolean isExternal;
    private String domain;
    private Search[] searches;
    private Document[] documents;
    private News[] news;
    private Tool[] tools;

    private UserAction() {}

    private UserAction(Builder builder) {
        this(
                builder.userAction,
                builder.userAction.searches,
                builder.userAction.documents,
                builder.userAction.news,
                builder.userAction.tools);
    }

    public UserAction(
            UserAction copy,
            @Nullable Search[] searches,
            @Nullable Document[] documents,
            @Nullable News[] news,
            @Nullable Tool[] tools) {
        this(
                copy.sessionId,
                copy.dateTime,
                copy.appId,
                copy.threadId,
                copy.userId,
                copy.userName,
                copy.userType,
                copy.accountId,
                copy.customerNumber,
                copy.groupId,
                copy.ipAddress,
                copy.isExternal,
                copy.domain,
                searches,
                documents,
                news,
                tools
        );
    }

    public UserAction(
            String sessionId,
            Date dateTime,
            String appId,
            String threadId,
            String userId,
            String userName,
            String userType,
            String accountId,
            String customerNumber,
            String groupId,
            String ipAddress,
            boolean isExternal,
            String domain,
            Search[] searches,
            Document[] documents,
            News[] news,
            Tool[] tools
    ) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.appId = appId;
        this.threadId = threadId;
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.accountId = accountId;
        this.customerNumber = customerNumber;
        this.groupId = groupId;
        this.ipAddress = ipAddress;
        this.isExternal = isExternal;
        this.domain = domain;
        this.searches = searches;
        this.documents = documents;
        this.news = news;
        this.tools = tools;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getAppId() {
        return appId;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public String getDomain() {
        return domain;
    }

    public List<Search> getSearches() {
        if (ArrayUtils.isNotEmpty(searches)) {
            return Arrays.asList(searches);
        }
        //noinspection unchecked
        return ListUtils.EMPTY_LIST;
    }

    public List<Document> getDocuments() {
        if (ArrayUtils.isNotEmpty(documents)) {
            return Arrays.asList(documents);
        }
        //noinspection unchecked
        return ListUtils.EMPTY_LIST;
    }
    public List<News> getNews() {
        if (ArrayUtils.isNotEmpty(news)) {
            return Arrays.asList(news);
        }
        //noinspection unchecked
        return ListUtils.EMPTY_LIST;
    }
    public List<Tool> getTools() {
        if (ArrayUtils.isNotEmpty(tools)) {
            return Arrays.asList(tools);
        }
        //noinspection unchecked
        return ListUtils.EMPTY_LIST;
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
        <time>20160808.000636165</time>
        <tremorId>i0acc372300000156688b9e33dbea4062</tremorId>
        <type>LOGIN</type>
        <appId>20</appId>
        <threadId>c111xjwcpdvap#10405:1470421452209.857</threadId>
        <sessionId>66BFB7969C34A41788D63FCA44F1DB34</sessionId>
        <userId>623265</userId>
        <userType>1</userType>
        <accountId>32042</accountId>
        <accountCustomerNumber>0TTAEMPS</accountCustomerNumber>
        <groupId>32042</groupId>
        <userName>Sadguru.palakonda</userName>
        <ipAddress>10.29.160.235</ipAddress>
        <isExternal>true</isExternal>
        <domain>dev.checkpoint.thomsonreuters.com:80</domain>
    </record>
    */
    public static final class Builder {

        private static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern("yyyyMMdd.HHmmssSSS");

        private final UserAction userAction;

        private Builder() {
            this.userAction = new UserAction();
        }

        public UserAction build(org.jsoup.nodes.Document logEntry) {

            Element sessionId = logEntry.select("sessionId").first();
            if (sessionId != null) {
                this.userAction.sessionId = sessionId.html();
            }
            Element time = logEntry.select("time").first();
            if (time != null) {
                this.userAction.dateTime = DATE_FMT.parseDateTime(time.html()).toDate();
            }
            Element appId = logEntry.select("appId").first();
            if (appId != null) {
                this.userAction.appId = appId.html();
            }
            Element threadId = logEntry.select("threadId").first();
            if (threadId != null) {
                this.userAction.threadId = threadId.html();
            }
            Element userId = logEntry.select("userId").first();
            if (userId != null) {
                this.userAction.userId = userId.html();
            }
            Element userName = logEntry.select("userName").first();
            if (userName != null) {
                this.userAction.userName = userName.html();
            }
            Element userType = logEntry.select("userType").first();
            if (userType != null) {
                this.userAction.userType = userType.html();
            }
            Element accountId = logEntry.select("accountId").first();
            if (accountId != null) {
                this.userAction.accountId = accountId.html();
            }
            Element accountCustomerNumber = logEntry.select("accountCustomerNumber").first();
            if (accountCustomerNumber != null) {
                this.userAction.customerNumber = accountCustomerNumber.html();
            }
            Element groupId = logEntry.select("groupId").first();
            if (groupId != null) {
                this.userAction.groupId = groupId.html();
            }
            Element isExternal = logEntry.select("isExternal").first();
            if (isExternal != null) {
                this.userAction.isExternal = BooleanUtils.toBoolean(isExternal.html());
            }
            Element domain = logEntry.select("domain").first();
            if (domain != null) {
                this.userAction.domain = domain.html();
            }

            return new UserAction(this);

        }

    }
}
