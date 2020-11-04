package com.sky.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.sky.framework.json.model.Info;
import com.sky.framework.json.model.User;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @date 2020-11-04 004 13:50
 */
public class JsonViewSerializerTest {

    ObjectMapper sut;
    JsonViewSerializer serializer;

    @Before
    public void setup() {
        this.serializer = new JsonViewSerializer();
        sut = new ObjectMapper()
                .registerModule(new JsonViewModule(serializer))
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @Test
    public void testJsonIgnore() throws JsonProcessingException {
        Info info = new Info();
        info.setRemark("备注信息");
        info.setDocument("文档信息");
        User user = new User();
        user.setName("名称");
        user.setAge(30);
        user.setBirthday(new Date());
        user.setRemark("备注");
        user.setInfo(info);

        JsonView<User> jsonView = JsonView.with(user).onClass(User.class, Match.match()
                .exclude("age", "info.document")
                .transform("name", (User x, String y) -> {
                    HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
                    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
                    format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
                    format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
                    try {
                        return PinyinHelper.toHanYuPinyinString(y, format, "", false);
                    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                        badHanyuPinyinOutputFormatCombination.printStackTrace();
                        return y;
                    }
                })
                .fieldExpandInterface(new FieldExpandInterface() {
                    @Override
                    public void append(JsonGenerator jgen) {
                        try {
                            jgen.writeFieldName("expandField");
                            jgen.writeString("测试追加字段");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
                        String prefix = handlerPrefix(currentPath, fieldName);
                        System.out.println(prefix);
                        if ("name".equals(prefix)) {
                            try {
                                jgen.writeFieldName("name");
                                jgen.writeString(value.toString() + "123456");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }

                        return false;
                    }

                    @Override
                    public void fieldIntensify(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
                        if (null == value) {
                            return;
                        }
                        String prefix = handlerPrefix(currentPath, fieldName);
                        System.out.println(prefix);
                        if ("remark".equals(prefix)) {
                            String text = (String) value;
                            if (StringUtils.isEmpty(text)) {
                                return;
                            }
                            try {
                                jgen.writeFieldName(fieldName + "Intensify");
                                jgen.writeString(text);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }));
        String writeValueAsString = sut.writeValueAsString(jsonView);
        System.out.println(writeValueAsString);

        String writeValueAsString1 = sut.writeValueAsString(user);
        System.out.println(writeValueAsString1);
    }

    private String handlerPrefix(String currentPath, String fieldName) {
        String prefix = currentPath.length() > 0 ? currentPath + "." : "";
        return prefix + fieldName;
    }
}
