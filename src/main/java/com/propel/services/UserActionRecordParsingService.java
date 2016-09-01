package com.propel.services;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.propel.entity.*;
import com.propel.utils.LogFileUtils;
import com.propel.utils.UserActionRecordBuilderUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by U0011960 on 8/23/16.
 */
public class UserActionRecordParsingService implements RecordParsingService<UserAction> {

    private static final Log logger = LogFactory.getLog(BrowserRecordParsingService.class);

    public UserActionRecordParsingService() {}

    @Override
    public List<UserAction> parse(File logfile, Pattern pattern) {

        Map<String, UserAction> userActionMap = new HashMap<>();
        Multimap<String, Search> searchMultiMap = ArrayListMultimap.create();
        Multimap<String, Document> documentMultiMap = ArrayListMultimap.create();
        Multimap<String, News> newsMultimap = ArrayListMultimap.create();
        Multimap<String, Tool> toolMultimapMap = ArrayListMultimap.create();

        List<InputStream> fileInputStreams = LogFileUtils.getFileInputStream(logfile, pattern);
        for (InputStream fileInputStream : fileInputStreams) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {

                String readline;
                while ((readline = reader.readLine()) != null) {

                    populateRecordMaps(userActionMap, searchMultiMap, documentMultiMap, newsMultimap, toolMultimapMap, readline);

                }

            } catch (IOException exception) {
                logger.info("error reading file " + logfile.getName(), exception);
            }
        }

        // assemble final records
        return createRecords(userActionMap, searchMultiMap, documentMultiMap, newsMultimap, toolMultimapMap);
    }

    private void populateRecordMaps(
            Map<String, UserAction> userActionMap,
            Multimap<String, Search> searchMultiMap,
            Multimap<String, Document> documentMultiMap,
            Multimap<String, News> newsMultimap,
            Multimap<String, Tool> toolMultimapMap,
            String readline) {

        UserActionRecordBuilderUtils recordBuilderUtils = new UserActionRecordBuilderUtils(readline);

        UserAction userAction = recordBuilderUtils.buildUserAction();
        if (userAction != null && StringUtils.isNotBlank(userAction.getSessionId())) {
            userActionMap.put(userAction.getSessionId(), userAction);
        }

        Search search = recordBuilderUtils.buildSearch();
        if (search != null && StringUtils.isNotBlank(search.getSessionId())) {
            searchMultiMap.put(search.getSessionId(), search);
        }

        Document document = recordBuilderUtils.buildDocument();
        if (document != null && StringUtils.isNotBlank(document.getSessionId())) {
            documentMultiMap.put(document.getSessionId(), document);
        }

        News news = recordBuilderUtils.buildNews();
        if (news != null && StringUtils.isNotBlank(news.getSessionId())) {
            newsMultimap.put(news.getSessionId(), news);
        }

        Tool tool = recordBuilderUtils.buildTool();
        if (tool != null && StringUtils.isNotBlank(tool.getSessionId())) {
            toolMultimapMap.put(tool.getSessionId(), tool);
        }

    }

    private List<UserAction> createRecords(Map<String, UserAction> userActionMap, Multimap<String, Search> searchMultiMap, Multimap<String, Document> documentMultiMap, Multimap<String, News> newsMultimap, Multimap<String, Tool> toolMultimapMap) {

        List<UserAction> records = new ArrayList<>();

        for (Map.Entry<String, UserAction> userActionEntry : userActionMap.entrySet()) {
            // pull up all searches
            Collection<Search> searches = searchMultiMap.get(userActionEntry.getKey());
            Search[] searchArrays = CollectionUtils.isNotEmpty(searches) ?
                    searches.toArray(new Search[searches.size()]) :
                    null;

            // pull up all documents
            Collection<Document> documents = documentMultiMap.get(userActionEntry.getKey());
            Document[] documentArrays = CollectionUtils.isNotEmpty(documents) ?
                    documents.toArray(new Document[documents.size()]) :
                    null;

            // pull up all news
            Collection<News> news = newsMultimap.get(userActionEntry.getKey());
            News[] newsArrays = CollectionUtils.isNotEmpty(news) ?
                    news.toArray(new News[news.size()]) :
                    null;

            // pull up all tools
            Collection<Tool> tools = toolMultimapMap.get(userActionEntry.getKey());
            Tool[] toolArrays = CollectionUtils.isNotEmpty(tools) ?
                    tools.toArray(new Tool[tools.size()]) :
                    null;


            records.add(new UserAction(userActionEntry.getValue(), searchArrays, documentArrays, newsArrays, toolArrays));
        }
        return records;
    }

}
