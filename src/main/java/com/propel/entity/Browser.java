package com.propel.entity;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by U0011960 on 8/22/16.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "browsers")
public class Browser {

    @Id
    private String sessionId;
    private Date date;
    private String machineName;
    private String serverId;
    private String userId;
    private String userName;
    private String userType;
    private String threadId;
    private boolean isBrowserHawkMobileBrowser;
    private boolean isLegacyiPadBrowser;
    private boolean isMobileBrowser;
    private boolean isTabletBrowser;
    private String userAgent;
    private String browser;
    private String containerBrowser;
    private String containerFullversion;
    private boolean isCookiesEnable;
    private String deviceName;
    private boolean isFrames;
    private String fullversion;
    private String ipAddress;
    private boolean isJavascriptSupported;
    private boolean isMouseOver;
    private String oSDetails;
    private String platForm;
    private boolean isStyleSheetEnable;
    private boolean isTablesEnabled;
    private boolean isWAP;
    private boolean isAjaxEnable; //XMLHttpRequest
    private String browserBuild;
    private String colorDepth;
    private int height;
    private int heightAvailable;
    private boolean isHighSecurity;
    private boolean isIECompatibilityMode;
    private boolean isImagesEnabled;
    private boolean isJavaScriptEnabled;
    private boolean isPersistentCookies;
    private String pluginFlash;
    private String pluginFlashVersion;
    private boolean isSessionCookiesEnabled;
    private int width;
    private int widthAvailable;
    private int connectionSpeed;

    private Browser() {}

    private Browser(Builder builder) {
        this(
                builder.browser.sessionId,
                builder.browser.date,
                builder.browser.machineName,
                builder.browser.serverId,
                builder.browser.userId,
                builder.browser.userName,
                builder.browser.userType,
                builder.browser.threadId,
                builder.browser.isBrowserHawkMobileBrowser,
                builder.browser.isLegacyiPadBrowser,
                builder.browser.isMobileBrowser,
                builder.browser.isTabletBrowser,
                builder.browser.userAgent,
                builder.browser.browser,
                builder.browser.containerBrowser,
                builder.browser.containerFullversion,
                builder.browser.isCookiesEnable,
                builder.browser.deviceName,
                builder.browser.isFrames,
                builder.browser.fullversion,
                builder.browser.ipAddress,
                builder.browser.isJavascriptSupported,
                builder.browser.isMouseOver,
                builder.browser.oSDetails,
                builder.browser.platForm,
                builder.browser.isStyleSheetEnable,
                builder.browser.isTablesEnabled,
                builder.browser.isWAP,
                builder.browser.isAjaxEnable,
                builder.browser.browserBuild,
                builder.browser.colorDepth,
                builder.browser.height,
                builder.browser.heightAvailable,
                builder.browser.isHighSecurity,
                builder.browser.isIECompatibilityMode,
                builder.browser.isImagesEnabled,
                builder.browser.isJavaScriptEnabled,
                builder.browser.isPersistentCookies,
                builder.browser.pluginFlash,
                builder.browser.pluginFlashVersion,
                builder.browser.isSessionCookiesEnabled,
                builder.browser.width,
                builder.browser.widthAvailable,
                builder.browser.connectionSpeed
        );
    }

    /**
     * Constructor called when spring mongo loads data from mongo db.
     */
    public Browser(
            String sessionId,
            Date date,
            String machineName,
            String serverId,
            String userId,
            String userName,
            String userType,
            String threadId,
            boolean isBrowserHawkMobileBrowser,
            boolean isLegacyiPadBrowser,
            boolean isMobileBrowser,
            boolean isTabletBrowser,
            String userAgent,
            String browser,
            String containerBrowser,
            String containerFullversion,
            boolean isCookiesEnable,
            String deviceName,
            boolean isFrames,
            String fullversion,
            String ipAddress,
            boolean isJavascriptSupported,
            boolean isMouseOver,
            String oSDetails,
            String platForm,
            boolean isStyleSheetEnable,
            boolean isTablesEnabled,
            boolean isWAP,
            boolean isAjaxEnable,
            String browserBuild,
            String colorDepth,
            int height,
            int heightAvailable,
            boolean isHighSecurity,
            boolean isIECompatibilityMode,
            boolean isImagesEnabled,
            boolean isJavaScriptEnabled,
            boolean isPersistentCookies,
            String pluginFlash,
            String pluginFlashVersion,
            boolean isSessionCookiesEnabled,
            int width,
            int widthAvailable,
            int connectionSpeed

    ) {
        this.sessionId = sessionId;
        this.date = date;
        this.machineName = machineName;
        this.serverId = serverId;
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.threadId = threadId;
        this.isBrowserHawkMobileBrowser = isBrowserHawkMobileBrowser;
        this.isLegacyiPadBrowser = isLegacyiPadBrowser;
        this.isMobileBrowser = isMobileBrowser;
        this.isTabletBrowser = isTabletBrowser;
        this.userAgent = userAgent;
        this.browser = browser;
        this.containerBrowser = containerBrowser;
        this.containerFullversion = containerFullversion;
        this.isCookiesEnable = isCookiesEnable;
        this.deviceName = deviceName;
        this.isFrames = isFrames;
        this.fullversion = fullversion;
        this.ipAddress = ipAddress;
        this.isJavascriptSupported = isJavascriptSupported;
        this.isMouseOver = isMouseOver;
        this.oSDetails = oSDetails;
        this.platForm = platForm;
        this.isStyleSheetEnable = isStyleSheetEnable;
        this.isTablesEnabled = isTablesEnabled;
        this.isWAP = isWAP;
        this.isAjaxEnable = isAjaxEnable;
        this.browserBuild = browserBuild;
        this.colorDepth = colorDepth;
        this.height = height;
        this.heightAvailable = heightAvailable;
        this.isHighSecurity = isHighSecurity;
        this.isIECompatibilityMode = isIECompatibilityMode;
        this.isImagesEnabled = isImagesEnabled;
        this.isJavaScriptEnabled = isJavaScriptEnabled;
        this.isPersistentCookies = isPersistentCookies;
        this.pluginFlash = pluginFlash;
        this.pluginFlashVersion = pluginFlashVersion;
        this.isSessionCookiesEnabled = isSessionCookiesEnabled;
        this.width = width;
        this.widthAvailable = widthAvailable;
        this.connectionSpeed = connectionSpeed;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getDate() {
        return date;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getServerId() {
        return serverId;
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

    public String getThreadId() {
        return threadId;
    }

    public boolean isBrowserHawkMobileBrowser() {
        return isBrowserHawkMobileBrowser;
    }

    public boolean isLegacyiPadBrowser() {
        return isLegacyiPadBrowser;
    }

    public boolean isMobileBrowser() {
        return isMobileBrowser;
    }

    public boolean isTabletBrowser() {
        return isTabletBrowser;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getBrowser() {
        return browser;
    }

    public String getContainerBrowser() {
        return containerBrowser;
    }

    public String getContainerFullversion() {
        return containerFullversion;
    }

    public boolean isCookiesEnable() {
        return isCookiesEnable;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean isFrames() {
        return isFrames;
    }

    public String getFullversion() {
        return fullversion;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public boolean isJavascriptSupported() {
        return isJavascriptSupported;
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }

    public String getoSDetails() {
        return oSDetails;
    }

    public String getPlatForm() {
        return platForm;
    }

    public boolean isStyleSheetEnable() {
        return isStyleSheetEnable;
    }

    public boolean isTablesEnabled() {
        return isTablesEnabled;
    }

    public boolean isWAP() {
        return isWAP;
    }

    public boolean isAjaxEnable() {
        return isAjaxEnable;
    }

    public String getBrowserBuild() {
        return browserBuild;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public int getHeight() {
        return height;
    }

    public int getHeightAvailable() {
        return heightAvailable;
    }

    public boolean isHighSecurity() {
        return isHighSecurity;
    }

    public boolean isIECompatibilityMode() {
        return isIECompatibilityMode;
    }

    public boolean isImagesEnabled() {
        return isImagesEnabled;
    }

    public boolean isJavaScriptEnabled() {
        return isJavaScriptEnabled;
    }

    public boolean isPersistentCookies() {
        return isPersistentCookies;
    }

    public String getPluginFlash() {
        return pluginFlash;
    }

    public String getPluginFlashVersion() {
        return pluginFlashVersion;
    }

    public boolean isSessionCookiesEnabled() {
        return isSessionCookiesEnabled;
    }

    public int getWidth() {
        return width;
    }

    public int getWidthAvailable() {
        return widthAvailable;
    }

    public int getConnectionSpeed() {
        return connectionSpeed;
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


    public static final class Builder {

        private static final Log logger = LogFactory.getLog(Builder.class);

        private static final Pattern LOG_HOUR_PATTERN = Pattern.compile("(\\d+?:\\d+?:\\d+?:\\d+?\\s\\w+)");
        private static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern("MM/dd/yyyy h:m:s:SSS a");

        private static final String LOG_ENTRY_HEAD = "*** ";

        private final Browser browser;
        private String logDate;

        private Builder() {
            this.browser = new Browser();
        }

        public Builder logDate(String logDate) {
            this.logDate = logDate;
            return this;
        }

        @Nullable
        public Browser buildFromLogEntry(String readline) {

            if (!StringUtils.startsWith(readline, LOG_ENTRY_HEAD)) {
                return null;
            }

            List<String> browserProperties = this.parseLogEntry(StringUtils.removeStart(readline, LOG_ENTRY_HEAD));
            // *** 12:06:37:661 AM;
            // machineName=1448377072427;
            // serverid=32104;
            // userid=623265;
            // username=Sadguru.palakonda;
            // userType=1;
            // sessId=66BFB7969C34A41788D63FCA44F1DB34;
            // threadId=c111xjwcpdvap#10405:1470421452209.860;
            // isBrowserHawkMobileBrowser=false;
            // isLegacyiPadBrowser=false;
            // isMobileBrowser=false;
            // isTabletBrowser=false;
            // userAgent=Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36;
            // Browser=Chrome;
            // ContainerBrowser=null;
            // ContainerFullversion=null;
            // Cookies=true;
            // DeviceName=;
            // Frames=true;
            // Fullversion=51.0.2704.103;
            // IPAddr=10.29.160.235;
            // JavaScript=true;
            // MouseOver=true;
            // OSDetails=Windows 7;
            // Platform=Windows 7;
            // StyleSheets=true;
            // Tables=true;
            // WAP=false;
            // XMLHttpRequest=true;
            // BrowserBuild=null;
            // ColorDepth=24;
            // Height=1080;
            // HeightAvail=759;
            // HighSecurity=false;
            // IECompatibilityMode=false;
            // ImagesEnabled=true;
            // JavaScriptEnabled=true;
            // PersistentCookies=true;
            // Plugin_Flash=22;
            // Plugin_FlashVerEx=22.0 r0;
            // SessionCookies=true;
            // Width=1920;
            // WidthAvail=1536;
            // ConnectionSpeed=853098
            for (String browserProperty : browserProperties) {

                if (LOG_HOUR_PATTERN.matcher(browserProperty).find()) {
                    // do crazy stuff to get the date right.....
                    // the log entry contains only the time
                    this.browser.date = createLogDate(browserProperty);
                    continue;
                }

                String[] nameValuePairs = browserProperty.split("=");
                if (ArrayUtils.getLength(nameValuePairs) == 2) {
                    switch (nameValuePairs[0]) {
                        case "machineName":
                            this.browser.machineName = nameValuePairs[1];
                            break;
                        case "serverid":
                            this.browser.serverId = nameValuePairs[1];
                            break;
                        case "userid":
                            this.browser.userId = nameValuePairs[1];
                            break;
                        case "username":
                            this.browser.userName = nameValuePairs[1];
                            break;
                        case "userType":
                            this.browser.userType = nameValuePairs[1];
                            break;
                        case "sessId":
                            this.browser.sessionId = nameValuePairs[1];
                            break;
                        case "threadId":
                            this.browser.threadId = nameValuePairs[1];
                            break;
                        case "isBrowserHawkMobileBrowser":
                            this.browser.isBrowserHawkMobileBrowser = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "isLegacyiPadBrowser":
                            this.browser.isLegacyiPadBrowser = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "isMobileBrowser":
                            this.browser.isMobileBrowser = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "isTabletBrowser":
                            this.browser.isTabletBrowser = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "userAgent":
                            this.browser.userAgent = nameValuePairs[1];
                            break;
                        case "Browser":
                            this.browser.browser = nameValuePairs[1];
                            break;
                        case "ContainerBrowser":
                            this.browser.containerBrowser = nameValuePairs[1];
                            break;
                        case "ContainerFullversion":
                            this.browser.containerFullversion = nameValuePairs[1];
                            break;
                        case "Cookies":
                            this.browser.isCookiesEnable = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "DeviceName":
                            this.browser.deviceName = nameValuePairs[1];
                            break;
                        case "Frames":
                            this.browser.isFrames = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "Fullversion":
                            this.browser.fullversion = nameValuePairs[1];
                            break;
                        case "IPAddr":
                            this.browser.ipAddress = nameValuePairs[1];
                            break;
                        case "JavaScript":
                            this.browser.isJavascriptSupported = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "MouseOver":
                            this.browser.isMouseOver = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "OSDetails":
                            this.browser.oSDetails = nameValuePairs[1];
                            break;
                        case "Platform":
                            this.browser.platForm = nameValuePairs[1];
                            break;
                        case "StyleSheets":
                            this.browser.isStyleSheetEnable = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "Tables":
                            this.browser.isTablesEnabled = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "WAP":
                            this.browser.isWAP = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "XMLHttpRequest":
                            this.browser.isAjaxEnable = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "BrowserBuild":
                            this.browser.browserBuild = nameValuePairs[1];
                            break;
                        case "ColorDepth":
                            this.browser.colorDepth = nameValuePairs[1];
                            break;
                        case "Height":
                            if (StringUtils.isNumeric(nameValuePairs[1])) {
                                this.browser.height = Integer.valueOf(nameValuePairs[1]);
                            }
                            break;
                        case "HeightAvail":
                            if (StringUtils.isNumeric(nameValuePairs[1])) {
                                this.browser.heightAvailable = Integer.valueOf(nameValuePairs[1]);
                            }
                            break;
                        case "HighSecurity":
                            this.browser.isHighSecurity = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "IECompatibilityMode":
                            this.browser.isIECompatibilityMode = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "ImagesEnabled":
                            this.browser.isImagesEnabled = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "JavaScriptEnabled":
                            this.browser.isJavaScriptEnabled = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "PersistentCookies":
                            this.browser.isPersistentCookies = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "Plugin_Flash":
                            this.browser.pluginFlash = nameValuePairs[1];
                            break;
                        case "Plugin_FlashVerEx":
                            this.browser.pluginFlashVersion = nameValuePairs[1];
                            break;
                        case "SessionCookies":
                            this.browser.isSessionCookiesEnabled = BooleanUtils.toBoolean(nameValuePairs[1]);
                            break;
                        case "Width":
                            if (StringUtils.isNumeric(nameValuePairs[1])) {
                                this.browser.width = Integer.valueOf(nameValuePairs[1]);
                            }
                            break;
                        case "WidthAvail":
                            if (StringUtils.isNumeric(nameValuePairs[1])) {
                                this.browser.widthAvailable = Integer.valueOf(nameValuePairs[1]);
                            }
                            break;
                        case "ConnectionSpeed":
                            if (StringUtils.isNumeric(nameValuePairs[1])) {
                                this.browser.connectionSpeed = Integer.valueOf(nameValuePairs[1]);
                            }
                            break;
                    }
                }
            }

            return new Browser(this);
        }

        @Nullable
        private Date createLogDate(String nameValuePair) {
            String dtString = String.format("%s %s", logDate, nameValuePair);
            if (StringUtils.isNotBlank(dtString)) {
                try {
                    return DATE_FMT.parseDateTime(dtString).toDate();
                } catch (RuntimeException rex) {
                    logger.info("Date Parsing Error", rex);
                }
            }
            return null;
        }

        private List<String> parseLogEntry(String readline) {
            List<String> entries = new ArrayList<>();

            int entryBeforeUserAgent = StringUtils.indexOfIgnoreCase(readline, "userAgent=");
            int entyAfterUserAgent = StringUtils.indexOfIgnoreCase(readline, " Browser=") + 1;

            entries.addAll(Arrays.asList(StringUtils.substring(readline, 0, entryBeforeUserAgent).split("; ")));
            entries.add(StringUtils.removeEnd(StringUtils.substring(readline, entryBeforeUserAgent, entyAfterUserAgent), ";"));
            entries.addAll(Arrays.asList(StringUtils.substring(readline, entyAfterUserAgent).split("; ")));

            return entries;
        }
    }
}

