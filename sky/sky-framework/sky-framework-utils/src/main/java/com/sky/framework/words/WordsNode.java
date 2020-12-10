package com.sky.framework.words;

import java.util.*;

/**
 * @date 2020-12-09 009 19:29
 */
public class WordsNode {

    private int layer;
    private boolean end;
    private char c;
    private long id;
    private Map<Character, WordsNode> nodeMap;
    private WordsNode parent;

    public WordsNode() {
        nodeMap = new HashMap<>(16);
    }

    /**
     * 新增字符
     *
     * @param c c
     * @return WordsNode
     */
    public WordsNode add(final Character c) {
        if (nodeMap.containsKey(c)) {
            return nodeMap.get(c);
        }
        final WordsNode node = new WordsNode();
        node.parent = this;
        node.c = c;
        nodeMap.put(c, node);
        return node;
    }

    public boolean hasKey(final char c) {
        return nodeMap.containsKey(c);
    }

    public WordsNode getNode(final char c) {
        return nodeMap.get(c);
    }

    /**
     * 获取当前节点的文本
     *
     * @return String
     */
    public String getWords() {
        if ('\u0000' == this.c) {
            return "";
        }
        List<String> words = new ArrayList<>(this.layer);
        words.add(String.valueOf(this.c));
        if (null != this.parent) {
            words.add(this.parent.getWords());
        }
        Collections.reverse(words);
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word);
        }
        return builder.toString();
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Character, WordsNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<Character, WordsNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public WordsNode getParent() {
        return parent;
    }

    public void setParent(WordsNode parent) {
        this.parent = parent;
    }
}
