package com.sky.framework.words;

/**
 * @date 2020-12-10 010 13:28
 */
public class SearchNode {

    private String words;
    private int index;
    private int lastIndex;
    private long id;

    public SearchNode() {
    }

    public SearchNode(String words, int index, int lastIndex) {
        this.words = words;
        this.index = index;
        this.lastIndex = lastIndex;
    }

    public SearchNode(String words, int index, int lastIndex, long id) {
        this.words = words;
        this.index = index;
        this.lastIndex = lastIndex;
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
