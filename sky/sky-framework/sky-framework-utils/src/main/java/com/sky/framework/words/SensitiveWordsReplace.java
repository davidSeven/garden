package com.sky.framework.words;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2020-12-09 009 19:51
 */
public class SensitiveWordsReplace {

    /**
     * 所有关键字
     */
    protected static List<SensitiveWords> sensitiveWordsList;
    protected static Map<Long, SensitiveWords> sensitiveWordsMap;

    public static void init(List<SensitiveWords> sensitiveWordsList) {
        SensitiveWordsReplace.sensitiveWordsList = sensitiveWordsList;
        SensitiveWordsReplace.sensitiveWordsMap = new HashMap<>(sensitiveWordsList.size());
        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            SensitiveWordsReplace.sensitiveWordsMap.put(sensitiveWords.getId(), sensitiveWords);
        }
    }

    public static String findReplace(String text) {
        // 只能支持全文匹配
        List<SearchNode> searchNodeList = SensitiveWordsSearch.getInstance().findWords(text, true);
        if (CollectionUtils.isEmpty(searchNodeList)) {
            return text;
        }
        Map<Integer, SearchNode> searchNodeMap = new HashMap<>(searchNodeList.size());
        for (SearchNode searchNode : searchNodeList) {
            int index = searchNode.getIndex();
            searchNodeMap.put(index, searchNode);
        }
        StringBuilder builder = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            SearchNode searchNode = searchNodeMap.get(i);
            if (null != searchNode) {
                SensitiveWords sensitiveWords = SensitiveWordsReplace.sensitiveWordsMap.get(searchNode.getId());
                if (null != sensitiveWords) {
                    builder.append(sensitiveWords.getReplace());
                } else  {
                    int i1 = searchNode.getLastIndex() - searchNode.getIndex();
                    for (int j = 0; j < i1; j++) {
                        builder.append("*");
                    }
                }
                i = searchNode.getLastIndex() - 1;
            } else {
                builder.append(text.charAt(i));
            }
        }
        return builder.toString();
    }
}
