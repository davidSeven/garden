package com.sky.framework.api.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @date 2020-11-09 009 14:56
 */
public class JsonContext {

    private Set<String> includes = new HashSet<>();
    private Set<String> excludes = new HashSet<>();
    // 敏感字段
    private Set<String> sensitives = new HashSet<>();
    // 加密字段
    private Set<String> encrypts = new HashSet<>();
    // 增强字段
    private Map<String, JsonIntensify> jsonIntensifyMap = new HashMap<>();
    // 字段值转换
    private Map<String, JsonIntensify> transformMap = new HashMap<>();
    // 添加字段
    private Map<String, JsonIntensify> appendMap = new HashMap<>();

    public JsonContext() {
    }

    public JsonContext(Set<String> includes, Set<String> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

    public JsonContext(Set<String> includes, Set<String> excludes, Set<String> sensitives, Set<String> encrypts, Map<String, JsonIntensify> jsonIntensifyMap) {
        this.includes = includes;
        this.excludes = excludes;
        this.sensitives = sensitives;
        this.encrypts = encrypts;
        this.jsonIntensifyMap = jsonIntensifyMap;
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

    public Set<String> getSensitives() {
        return sensitives;
    }

    public void setSensitives(Set<String> sensitives) {
        this.sensitives = sensitives;
    }

    public Set<String> getEncrypts() {
        return encrypts;
    }

    public void setEncrypts(Set<String> encrypts) {
        this.encrypts = encrypts;
    }

    public Map<String, JsonIntensify> getJsonIntensifyMap() {
        return jsonIntensifyMap;
    }

    public void setJsonIntensifyMap(Map<String, JsonIntensify> jsonIntensifyMap) {
        this.jsonIntensifyMap = jsonIntensifyMap;
    }

    public Map<String, JsonIntensify> getTransformMap() {
        return transformMap;
    }

    public void setTransformMap(Map<String, JsonIntensify> transformMap) {
        this.transformMap = transformMap;
    }

    public Map<String, JsonIntensify> getAppendMap() {
        return appendMap;
    }

    public void setAppendMap(Map<String, JsonIntensify> appendMap) {
        this.appendMap = appendMap;
    }
}
