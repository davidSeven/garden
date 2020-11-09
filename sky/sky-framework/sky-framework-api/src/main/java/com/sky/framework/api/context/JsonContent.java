package com.sky.framework.api.context;

import java.util.HashSet;
import java.util.Set;

/**
 * @date 2020-11-09 009 14:56
 */
public class JsonContent {

    private Set<String> includes = new HashSet<>();
    private Set<String> excludes = new HashSet<>();

    public JsonContent() {
    }

    public JsonContent(Set<String> includes, Set<String> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

    public Set<String> getIncludes() {
        return includes;
    }

    public void setIncludes(Set<String> includes) {
        this.includes = includes;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
    }
}
