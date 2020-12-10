package com.sky.framework.words;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020-12-09 009 19:36
 */
public class SensitiveWordsSearch {

    /**
     * 关键字根节点
     */
    protected WordsNode rootNode;
    /**
     * 所有关键字
     */
    protected List<SensitiveWords> sensitiveWordsList;
    /**
     * 关键字加载中
     */
    protected boolean keywordsLoading;

    private SensitiveWordsSearch() {
    }

    /**
     * 获取实例
     *
     * @return SensitiveWordsSearch
     */
    public static SensitiveWordsSearch getInstance() {
        return SensitiveWordsSearchInstance.INSTANCE;
    }

    /**
     * 初始化关键字
     */
    private void initKeywords() {
        // 初始化
        rootNode = new WordsNode();
        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            WordsNode node = rootNode;
            String words = sensitiveWords.getWords();
            int length = words.length();
            for (int i = 0; i < length; i++) {
                node = node.add(words.charAt(i));
                if (node.getLayer() == 0) {
                    node.setLayer(i + 1);
                }
            }
            node.setEnd(true);
            node.setId(sensitiveWords.getId());
        }

        System.out.println(JSON.toJSONString(rootNode));
    }

    /**
     * 更新关键字
     *
     * @param sensitiveWordsList 关键字集合
     */
    public void updateKeywords(List<SensitiveWords> sensitiveWordsList) {
        if (!this.keywordsLoading) {
            this.keywordsLoading = true;
            this.sensitiveWordsList = sensitiveWordsList;
            this.initKeywords();
            this.keywordsLoading = false;
        }
    }

    /**
     * 获取关键字
     *
     * @param text     检索文本
     * @param maxMatch 最大匹配
     * @return 查找到的关键字
     */
    public List<SearchNode> findWords(String text, boolean maxMatch) {
        if (null == rootNode) {
            throw new RuntimeException("SensitiveWordsSearch uninitialized.");
        }
        WordsNode top = null;
        List<SearchNode> list = new ArrayList<>();
        WordsNode preNode = null;
        int length = text.length();
        int lastLength = length - 1;
        for (int i = 0; i < length; i++) {
            final char t = text.charAt(i);
            WordsNode node;
            if (top == null) {
                node = rootNode.getNode(t);
            } else {
                if (top.hasKey(t)) {
                    node = top.getNode(t);
                } else {
                    if (maxMatch && top.isEnd()) {
                        preNode = top;
                    }
                    node = rootNode.getNode(t);
                }
            }
            if (maxMatch) {
                // 下一个节点
                if (preNode != null) {
                    // 计算层级向前
                    list.add(new SearchNode(preNode.getWords(), i - preNode.getLayer(), i, preNode.getId()));
                    preNode = null;
                }
            } else {
                // 当前节点
                if (node != null && node.isEnd()) {
                    list.add(new SearchNode(node.getWords(), i + 1 - node.getLayer(), i + 1, node.getId()));
                }
            }
            // 最大匹配时修正最后一个文本无法匹配的问题
            if (lastLength == i && maxMatch && node != null && node.isEnd()) {
                // 当前节点
                // 最后匹配
                list.add(new SearchNode(node.getWords(), i + 1 - node.getLayer(), i + 1, node.getId()));
            }
            top = node;
        }
        return list;
    }

    /**
     * 静态内部类
     */
    private static class SensitiveWordsSearchInstance {
        /**
         * 实例对象
         */
        private static final SensitiveWordsSearch INSTANCE = new SensitiveWordsSearch();
    }
}
