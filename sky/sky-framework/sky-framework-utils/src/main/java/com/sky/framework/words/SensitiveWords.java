package com.sky.framework.words;

/**
 * @date 2020-12-10 010 13:53
 */
public class SensitiveWords {

    private long id;
    private String words;
    private String replace;

    public SensitiveWords() {
    }

    public SensitiveWords(long id, String words, String replace) {
        this.id = id;
        this.words = words;
        this.replace = replace;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }
}
