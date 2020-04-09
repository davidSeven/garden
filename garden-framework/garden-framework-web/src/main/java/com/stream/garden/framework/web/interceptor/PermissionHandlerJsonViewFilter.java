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
    private HandlerFieldNameSerializer handlerFieldNameSerializer;

    public PermissionHandlerJsonViewFilter(FieldSerializer fieldSerializer, HandlerFieldNameSerializer handlerFieldNameSerializer) {
        if (null != fieldSerializer) {
            this.handlerFieldSet = fieldSerializer.getHandlerFieldSet();
            this.sensitiveFieldSet = fieldSerializer.getSensitiveFieldSet();
        }
        this.handlerFieldNameSerializer = handlerFieldNameSerializer;
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
        if (null == handlerFieldNameSerializer) {
            return;
        }
        if ("createdBy".equals(fieldName) || "updatedBy".equals(fieldName)) {
            String text = (String) value;
            if (StringUtils.isEmpty(text)) {
                return;
            }
            try {
                String name = handlerFieldNameSerializer.getName(text);
                jgen.writeFieldName(fieldName + "Name");
                jgen.writeString(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
