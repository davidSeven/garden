package com.sky.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.sky.framework.api.context.JsonContext;
import com.sky.framework.api.context.JsonIntensify;
import com.sky.framework.json.util.ApplicationBeanUtil;
import com.sky.framework.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
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

    // 转换器
    private ConcurrentMap<String, JsonIntensifyConvert> convertMap;

    public JsonIntensifyFieldExpandInterface(JsonContext jsonContext) {
        this.sensitives = jsonContext.getSensitives();
        this.encrypts = jsonContext.getEncrypts();
        this.jsonIntensifyMap = jsonContext.getJsonIntensifyMap();
        // 加载转换器
        loadConvert();
    }

    @Override
    public boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
        if (null == value) {
            return false;
        }
        String prefix = handlerPrefix(currentPath, fieldName);
        if (null != this.sensitives && this.sensitives.contains(prefix)) {
            String text = (String) value;
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
                e.printStackTrace();
            }
        }
        if (null != this.encrypts && this.encrypts.contains(prefix)) {
            String text = (String) value;
            if (StringUtils.isEmpty(text)) {
                return false;
            }
            try {
                jgen.writeFieldName(fieldName);
                jgen.writeString("***");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
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
            JsonIntensifyConvert jsonIntensifyConvert = this.convertMap.get(jsonIntensify.getFieldName());
            if (null != jsonIntensifyConvert) {
                String convertValue = jsonIntensifyConvert.convert(text);
                try {
                    // 增加增强字段
                    jgen.writeFieldName(jsonIntensify.getFieldIntensifyName());
                    jgen.writeString(convertValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadConvert() {
        if (null == this.jsonIntensifyMap) {
            return;
        }
        // 加载转换器
        this.convertMap = new ConcurrentHashMap<>();
        for (String key : this.jsonIntensifyMap.keySet()) {
            JsonIntensify jsonIntensify = this.jsonIntensifyMap.get(key);
            if (null == jsonIntensify) {
                continue;
            }
            JsonIntensifyConvert jsonIntensifyConvert = null;
            // 加载类
            if ("class".equals(jsonIntensify.getLoadClassType())) {
                jsonIntensifyConvert = this.loadClassConvert(jsonIntensify.getLoadClassPath());
            } else if ("springBean".equals(jsonIntensify.getLoadClassType())) {
                jsonIntensifyConvert = this.loadSpringClassConvert(jsonIntensify.getLoadClassPath());
            }
            if (null != jsonIntensifyConvert) {
                this.convertMap.put(key, jsonIntensifyConvert);
            }
        }
    }

    private JsonIntensifyConvert loadClassConvert(String classPath) {
        JsonIntensifyConvert jsonIntensifyConvert = null;
        try {
            Class<?> convertClass = Class.forName(classPath);
            jsonIntensifyConvert = (JsonIntensifyConvert) convertClass.newInstance();
        } catch (ClassNotFoundException e) {
            logger.error("转换器不存在", e);
        } catch (IllegalAccessException e) {
            logger.error("转换器访问权限不足", e);
        } catch (InstantiationException e) {
            logger.error("转换器无法实例化", e);
        }
        return jsonIntensifyConvert;
    }

    private JsonIntensifyConvert loadSpringClassConvert(String classPath) {
        JsonIntensifyConvert jsonIntensifyConvert = null;
        try {
            jsonIntensifyConvert = ApplicationBeanUtil.getBean(classPath, JsonIntensifyConvert.class);
        } catch (Exception e) {
            logger.error("获取springBean失败", e);
        }
        return jsonIntensifyConvert;
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

    public ConcurrentMap<String, JsonIntensifyConvert> getConvertMap() {
        return convertMap;
    }

    public void setConvertMap(ConcurrentMap<String, JsonIntensifyConvert> convertMap) {
        this.convertMap = convertMap;
    }
}
