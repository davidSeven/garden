package com.sky.framework.json.config;

import com.sky.framework.api.context.JsonContext;
import com.sky.framework.api.context.JsonIntensify;
import com.sky.framework.utils.BeanHelpUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = JsonContextConfiguration.CONFIG_PREFIX)
public class JsonContextConfiguration {
    static final String CONFIG_PREFIX = "json-context";

    // 路径配置
    private Map<String, JsonContextConfig> values;

    public Map<String, JsonContextConfig> getValues() {
        return values;
    }

    public void setValues(Map<String, JsonContextConfig> values) {
        this.values = values;
    }

    public JsonContext getJsonContext(String uri) {
        if (null == uri || "".equals(uri.trim()) || null == this.values || this.values.isEmpty()) {
            return null;
        }
        JsonContextConfig jsonContextConfig = this.values.get(uri);
        if (null == jsonContextConfig) {
            return null;
        }
        return BeanHelpUtil.convertDto(jsonContextConfig, JsonContext.class);
    }

    public static class JsonContextConfig {
        // 包含的字段
        private Set<String> includes;
        // 排除的字段
        private Set<String> excludes;
        // 敏感字段
        private Set<String> sensitives;
        // 加密字段
        private Set<String> encrypts;
        // 增强字段
        private Map<String, JsonIntensify> jsonIntensifyMap;
        // 字段值转换
        private Map<String, JsonIntensify> transformMap;
        // 添加字段
        private Map<String, JsonIntensify> appendMap;

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
}
