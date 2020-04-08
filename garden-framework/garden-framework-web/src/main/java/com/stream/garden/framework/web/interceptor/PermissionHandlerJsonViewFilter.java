package com.stream.garden.framework.web.interceptor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.stream.garden.framework.util.EncryptUtils;
import com.stream.garden.framework.web.json.HandlerJsonViewFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @author garden
 * @date 2020-04-08 10:46
 */
public class PermissionHandlerJsonViewFilter implements HandlerJsonViewFilter {

    private Set<String> handlerFieldSet;
    private Set<String> sensitiveFieldSet;

    public PermissionHandlerJsonViewFilter(Set<String> handlerFieldSet, Set<String> sensitiveFieldSet) {
        this.handlerFieldSet = handlerFieldSet;
        this.sensitiveFieldSet = sensitiveFieldSet;
    }

    @Override
    public boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
        if (null == handlerFieldSet && null == sensitiveFieldSet) {
            return false;
        }
        String prefix = currentPath.length() > 0 ? currentPath + "." : "";
        String name = prefix + fieldName;
        // 不可见字段
        if (null != handlerFieldSet && handlerFieldSet.contains(name)) {
            try {
                jgen.writeFieldName(fieldName);
                jgen.writeString("字段不可见");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        // 敏感字段
        if (null != sensitiveFieldSet && sensitiveFieldSet.contains(name)) {
            try {
                jgen.writeFieldName(fieldName);
                jgen.writeString((String) null);
                jgen.writeFieldName(fieldName + "Encoder");
                jgen.writeString(EncryptUtils.base64Encoder(value.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public void fieldIntensify(String fieldName, Object value, JsonGenerator jgen) {
        if (null == value) {
            return;
        }
        if ("createdBy".equals(fieldName) || "updatedBy".equals(fieldName)) {
            String text = (String) value;
            if (StringUtils.isEmpty(text)) {
                return;
            }
            if ("createdBy".equals(fieldName)) {
                try {
                    jgen.writeFieldName("createdByName");
                    jgen.writeString("张三");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("updatedBy".equals(fieldName)) {
                try {
                    jgen.writeFieldName("updatedByName");
                    jgen.writeString("李四");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
