package com.propel.entity;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by U0011960 on 8/23/16.
 */
public class Search {
    private String sessionId;
    private Date dateTime;
    private String searchType;
    private String practiceArea;
    private String keywords;
    private int resultCount;
    private String[] sources;
    private String searchTerm;
    private String templateId;
    private String tabLoc;

    private Search() {}

    private Search(Builder builder) {
        this(
                builder.search.sessionId,
                builder.search.dateTime,
                builder.search.searchType,
                builder.search.practiceArea,
                builder.search.keywords,
                builder.search.resultCount,
                builder.search.sources,
                builder.search.searchTerm,
                builder.search.templateId,
                builder.search.tabLoc
        );
    }

    public Search(
            String sessionId,
            Date dateTime,
            String searchType,
            String practiceArea,
            String keywords,
            int resultCount,
            String[] sources,
            String searchTerm,
            String templateId,
            String tabLoc

    ) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.searchType = searchType;
        this.practiceArea = practiceArea;
        this.keywords = keywords;
        this.resultCount = resultCount;
        this.sources = sources;
        this.searchTerm = searchTerm;
        this.templateId = templateId;
        this.tabLoc = tabLoc;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getSearchType() {
        return searchType;
    }

    public String getPracticeArea() {
        return practiceArea;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getResultCount() {
        return resultCount;
    }

    public String[] getSources() {
        return sources;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getTabLoc() {
        return tabLoc;
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
        <time>20160808.112713796</time>
        <tremorId>i0acc3723000001566afac0e9dbea5e00</tremorId>
        <type>SEARCH</type>
        <appId>20</appId>
        <threadId>c111xjwcpdvap#10405:1470421452209.3292</threadId>
        <sessionId>428609F1ED811045BA3A26E4B0F4221C</sessionId>
        <userId>4369630</userId>
        <userType>1</userType>
        <searchResultId>i0acc044a000001566afac169c75810ee</searchResultId>
        <search>
            <searchType>cobalt</searchType>
            <keywords>123</keywords>
            <displayQuery>123</displayQuery>
            <novusQuery>123</novusQuery>
            <novusView>=n-tocview(T0fedftqr)</novusView>
            <resultCount>0</resultCount>
            <tabLoc>1070</tabLoc>
            <collections>
                <collectionId>T0fedftqr</collectionId>
            </collections>
        </search>
    </record>
    <record>
        <time>20160808.100346845</time>
        <tremorId>i0acc3723000001566aae56c5dbea578c</tremorId>
        <type>SEARCH</type>
        <appId>20</appId>
        <threadId>c111xjwcpdvap#10405:1470421452209.2672</threadId>
        <sessionId>521F2221904716D08A0B7EEB2915738D</sessionId>
        <userId>623104</userId>
        <userType>1</userType>
        <searchResultId>i0acc044a000001566aae5b08c7580f0e</searchResultId>
        <search>
            <searchType>TC</searchType>
            <displayQuery>S. 05-917</displayQuery>
            <novusQuery>(=DCKT("S. 05-917") OR =CSREF("S. 05-917"))</novusQuery>
            <novusView>=n-tocview(T0ADVAL) =n-tocview(T0EIDAL) =n-tocview(T0slcsfed)</novusView>
            <resultCount>1</resultCount>
            <practiceArea>2</practiceArea>
            <collections>
                <collectionId>ADVAL</collectionId>
                <collectionId numDocs="1">SLAL</collectionId>
                <collectionId>SLCSFED</collectionId>
            </collections>
        </search>
    </record>
    <record>
        <time>20160808.100443588</time>
        <tremorId>i0acc3723000001566aaf36a4dbea57c4</tremorId>
        <type>SEARCH</type>
        <appId>13</appId>
        <threadId>c111xjwcpdvap#10405:1470421452209.2690</threadId>
        <sessionId>521F2221904716D08A0B7EEB2915738D</sessionId>
        <userId>623104</userId>
        <userType>1</userType>
        <searchResultId>i0ace0d1b000001566aaf391922d60abe</searchResultId>
        <search>
            <searchType>TC</searchType>
            <displayQuery>Redlark</displayQuery>
            <novusQuery>=TCRCAS&gt;SHNAME("Redlark") OR =TCMCAS&gt;SHNAME("Redlark") OR =TCSUMM&gt;SHNAME("Redlark") OR
                =AFTRCAS&gt;SHNAME("Redlark") OR =WTHDRN&gt;SHNAME("Redlark") OR =TCRCAS&gt;RNAME("Redlark") OR =TCMCAS&gt;RNAME("Redlark")
                OR =TCSUMM&gt;RNAME("Redlark") OR =AFTRCAS&gt;RNAME("Redlark") OR =WTHDRN&gt;RNAME("Redlark")
            </novusQuery>
            <novusView>=n-tocview(T0toc105) =n-tocview(T0toc106) =n-tocview(T0toc108) =n-tocview(T0toc109)
                =n-tocview(T0toc111) =n-tocview(T0toc112) =n-tocview(T0toc112a.1a)
            </novusView>
            <searchTerm id="1">Redlark</searchTerm>
            <templateId>2150|fedcit_FIELD26|cases</templateId>
            <resultCount>3</resultCount>
            <practiceArea>1</practiceArea>
            <tabLoc>2150</tabLoc>
            <collections>
                <collectionId>AFTRINC</collectionId>
                <collectionId numDocs="1">AFTRHIST</collectionId>
                <collectionId>TCR</collectionId>
                <collectionId numDocs="2">TCRHIST</collectionId>
                <collectionId>TCM</collectionId>
                <collectionId>TCMHIST</collectionId>
                <collectionId>TCSUM</collectionId>
            </collections>
        </search>
    </record>
*/
    public static final class Builder {

        private static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern("yyyyMMdd.HHmmssSSS");

        private final Search search;

        private Builder() {
            this.search = new Search();
        }

        public Search build(org.jsoup.nodes.Document logEntry) {

            Element sessionId = logEntry.select("sessionId").first();
            if (sessionId != null) {
                this.search.sessionId = sessionId.html();
            }
            Element time = logEntry.select("time").first();
            if (time != null) {
                this.search.dateTime = DATE_FMT.parseDateTime(time.html()).toDate();
            }
            Element searchType = logEntry.select("searchType").first();
            if (searchType != null) {
                this.search.searchType = searchType.html();
            }
            Element practiceArea = logEntry.select("practiceArea").first();
            if (practiceArea != null) {
                this.search.practiceArea = practiceArea.html();
            }

            Element keywords = logEntry.select("keywords").first();
            if (keywords != null) {
                this.search.keywords = keywords.html();
            }

            Element resultCount = logEntry.select("resultCount").first();
            if (resultCount != null && StringUtils.isNumeric(resultCount.html())) {
                this.search.resultCount = Integer.valueOf(resultCount.html());
            }

            Elements collections = logEntry.select("collectionId");
            if (collections.size() > 0) {
                List<String> sources = new ArrayList<>();
                for(Element collection : collections) {
                    sources.add(collection.html());
                }
                this.search.sources = sources.toArray(new String[sources.size()]);
            }

            Element searchTerm = logEntry.select("searchTerm").first();
            if (searchTerm != null) {
                this.search.searchTerm = searchTerm.html();
            }

            Element templateId = logEntry.select("templateId").first();
            if (templateId != null) {
                this.search.templateId = templateId.html();
            }

            Element tabLoc = logEntry.select("tabLoc").first();
            if (tabLoc != null) {
                this.search.tabLoc = tabLoc.html();
            }

            return new Search(this);

        }
    }
}
