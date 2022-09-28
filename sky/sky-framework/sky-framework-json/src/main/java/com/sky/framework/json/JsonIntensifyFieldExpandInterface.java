package com.sky.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.sky.framework.api.context.JsonContext;
import com.sky.framework.api.context.JsonIntensify;
import com.sky.framework.utils.EncryptUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @date 2020-11-10 010 14:03
 */
public class JsonIntensifyFieldExpandInterface implements FieldExpandInterface {
    private final Logger logger = LoggerFactory.getLogger(JsonIntensifyFieldExpandInterface.class);

    // 敏感字段
    private Set<String> sensitives;
    // 加密字段
    private Set<String> encrypts;
    // 增强字段
    private Map<String, JsonIntensify> jsonIntensifyMap;
    // 添加字段
    private Map<String, JsonIntensify> appendMap;
    // 增强字段转换器
    private ConcurrentMap<String, JsonIntensifyConvert> intensifyConvertMap;
    // 添加字段转换器
    private ConcurrentMap<String, JsonIntensifyAppend> appendConvertMap;

    public JsonIntensifyFieldExpandInterface(JsonContext jsonContext) {
        this.sensitives = jsonContext.getSensitives();
        this.encrypts = jsonContext.getEncrypts();
        this.appendMap = jsonContext.getAppendMap();
        this.jsonIntensifyMap = jsonContext.getJsonIntensifyMap();
        // 加载转换器
        loadConvert();
    }

    @Override
    public void append(String currentPath, Stack<String> path, JsonGenerator jgen, Object object) {
        if (null != appendMap && appendMap.containsKey(currentPath)) {
            JsonIntensify jsonIntensify = appendMap.get(currentPath);
            String jsonIntensifyFieldName = jsonIntensify.getFieldName();
            if (StringUtils.isEmpty(jsonIntensifyFieldName)) {
                jsonIntensifyFieldName = currentPath;
            }
            JsonIntensifyAppend jsonIntensifyAppend = this.appendConvertMap.get(jsonIntensifyFieldName);
            if (null != jsonIntensifyAppend) {
                String convertValue = jsonIntensifyAppend.convert(object);
                try {
                    // 增加增强字段
                    String fieldIntensifyName = jsonIntensify.getFieldIntensifyName();
                    if (StringUtils.isEmpty(fieldIntensifyName)) {
                        fieldIntensifyName = "_append";
                    }
                    jgen.writeFieldName(fieldIntensifyName);
                    jgen.writeString(convertValue);
                } catch (IOException e) {
                    logger.error("添加字段异常", e);
                }
            }
        }
    }

    @Override
    public boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
        if (null == value) {
            return false;
        }
        String prefix = handlerPrefix(currentPath, fieldName);
        if (null != this.sensitives && this.sensitives.contains(prefix)) {
            String text = String.valueOf(value);
            if (StringUtils.isEmpty(text)) {
                return false;
            }
            try {
                // 原字段还是需要返回
                jgen.writeFieldName(fieldName);
                jgen.writeString("***");
                // 密文
                jgen.writeFieldName(fieldName + "Sensitive");
                jgen.writeString(EncryptUtils.encrypt(text, "123456"));
                return true;
            } catch (IOException e) {
                logger.error("字段加密异常", e);
            }
        }
        if (null != this.encrypts && this.encrypts.contains(prefix)) {
            String text = String.valueOf(value);
            if (StringUtils.isEmpty(text)) {
                return false;
            }
            try {
                jgen.writeFieldName(fieldName);
                jgen.writeString("***");
                return true;
            } catch (IOException e) {
                logger.error("字段加密异常", e);
            }
        }
        return false;
    }

    @Override
    public void fieldIntensify(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
        if (null == value) {
            return;
        }
        String prefix = handlerPrefix(currentPath, fieldName);
        if (null != jsonIntensifyMap && jsonIntensifyMap.containsKey(prefix)) {
            String text = (String) value;
            if (StringUtils.isEmpty(text)) {
                return;
            }
            JsonIntensify jsonIntensify = jsonIntensifyMap.get(prefix);
            String jsonIntensifyFieldName = jsonIntensify.getFieldName();
            if (StringUtils.isEmpty(jsonIntensifyFieldName)) {
                jsonIntensifyFieldName = prefix;
            }
            JsonIntensifyConvert jsonIntensifyConvert = this.intensifyConvertMap.get(jsonIntensifyFieldName);
            if (null != jsonIntensifyConvert) {
                String convertValue = jsonIntensifyConvert.convert(text);
                try {
                    // 增加增强字段
                    jgen.writeFieldName(jsonIntensify.getFieldIntensifyName());
                    jgen.writeString(convertValue);
                } catch (IOException e) {
                    logger.error("增强字段异常", e);
                }
            }
        }
    }

    private void loadConvert() {
        // 加载转换器
        if (MapUtils.isNotEmpty(this.jsonIntensifyMap)) {
            this.intensifyConvertMap = new ConcurrentHashMap<>();
            for (String key : this.jsonIntensifyMap.keySet()) {
                JsonIntensify jsonIntensify = this.jsonIntensifyMap.get(key);
                if (null == jsonIntensify) {
                    continue;
                }
                // 加载类
                JsonIntensifyConvert jsonIntensifyConvert = JsonIntensifyUtil.load(jsonIntensify);
                if (null == jsonIntensifyConvert) {
                    jsonIntensifyConvert = new JsonIntensifyConvert.OriginalJsonIntensifyConvert();
                }
                this.intensifyConvertMap.put(key, jsonIntensifyConvert);
            }
        }
        if (MapUtils.isNotEmpty(this.appendMap)) {
            this.appendConvertMap = new ConcurrentHashMap<>();
            for (String key : this.appendMap.keySet()) {
                JsonIntensify jsonIntensify = this.appendMap.get(key);
                if (null == jsonIntensify) {
                    continue;
                }
                // 加载类
                JsonIntensifyAppend jsonIntensifyAppend = JsonIntensifyUtil.loadAppend(jsonIntensify);
                if (null == jsonIntensifyAppend) {
                    jsonIntensifyAppend = new JsonIntensifyAppend.OriginalJsonIntensifyAppend();
                }
                this.appendConvertMap.put(key, jsonIntensifyAppend);
            }
        }

    }

    private String handlerPrefix(String currentPath, String fieldName) {
        String prefix = currentPath.length() > 0 ? currentPath + "." : "";
        return prefix + fieldName;
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

    public ConcurrentMap<String, JsonIntensifyConvert> getIntensifyConvertMap() {
        return intensifyConvertMap;
    }

    public void setIntensifyConvertMap(ConcurrentMap<String, JsonIntensifyConvert> intensifyConvertMap) {
        this.intensifyConvertMap = intensifyConvertMap;
    }

    public Map<String, JsonIntensify> getAppendMap() {
        return appendMap;
    }

    public void setAppendMap(Map<String, JsonIntensify> appendMap) {
        this.appendMap = appendMap;
    }

    public ConcurrentMap<String, JsonIntensifyAppend> getAppendConvertMap() {
        return appendConvertMap;
    }

    public void setAppendConvertMap(ConcurrentMap<String, JsonIntensifyAppend> appendConvertMap) {
        this.appendConvertMap = appendConvertMap;
    }
}
