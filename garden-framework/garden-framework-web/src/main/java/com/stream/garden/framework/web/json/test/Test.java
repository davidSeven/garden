package com.stream.garden.framework.web.json.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.stream.garden.framework.util.EncryptUtils;
import com.stream.garden.framework.web.json.HandlerJsonView;
import com.stream.garden.framework.web.json.HandlerJsonViewFilter;
import com.stream.garden.framework.web.json.HandlerJsonViewModule;
import com.stream.garden.framework.web.json.HandlerJsonViewSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author garden
 * @date 2020-04-07 19:56
 */
public class Test {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 时间的转化格式,转换成时间戳
        // objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //
        // objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        HandlerJsonViewSerializer serializer = new HandlerJsonViewSerializer();
        objectMapper.registerModule(new HandlerJsonViewModule(serializer));

        // 转json的对象
        ObjectValue objectValue = new ObjectValue(333, "卡勒基");
        List<ObjectValue> objectValueList = new ArrayList<>();
        objectValueList.add(objectValue);
        User user = new User(3, "张三", true, "zs@qq.com", new Date(), objectValue, objectValueList);
        String value = null;

        Set<String> handlerFieldSet = new HashSet<>();
        handlerFieldSet.add("id");
        handlerFieldSet.add("objectValue.id");
        handlerFieldSet.add("objectValueList.id");

        Set<String> sensitiveFieldSet = new HashSet<>();
        sensitiveFieldSet.add("email");

        HandlerJsonView<User> handlerJsonView = new HandlerJsonView<>(user);
        handlerJsonView.setHandlerJsonViewFilter(new HandlerJsonViewFilter() {
            @Override
            public boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
                String prefix = currentPath.length() > 0 ? currentPath + "." : "";
                String name = prefix + fieldName;
                // 不可见字段
                if (handlerFieldSet.contains(name)) {
                    try {
                        jgen.writeFieldName(fieldName);
                        jgen.writeString("***");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                // 敏感字段
                if (sensitiveFieldSet.contains(name)) {
                    try {
                        jgen.writeFieldName(fieldName + "Encoder");
                        jgen.writeString(EncryptUtils.base64Encoder(value.toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        try {
            System.out.println(objectMapper.writeValueAsString(handlerJsonView));
            System.out.println(objectMapper.writeValueAsString(user));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
