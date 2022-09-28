package com.sky.framework.validator;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class TestValidator {

    @Test
    public void testMessage() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        DemoUser demoUser = new DemoUser();
        demoUser.setName("啊啊啊啊顶顶钉钉呃呃呃呃帆帆帆帆灌灌灌灌啊啊啊啊足足组织哇哇哇哇日日日日涛涛涛涛又由又有");
        demoUser.setHobby("aa");
        System.out.println(JSON.toJSONString(validate(demoUser, validator)));
    }

    public <T> Map<String, StringBuffer> validate(T obj, Validator validator) {
        Map<String, StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            errorMap = new HashMap<>();
            String property;
            for (ConstraintViolation<T> cv : set) {
                //这里循环获取错误信息，可以自定义格式
                property = cv.getPropertyPath().toString();
                /*if (StringUtils.isEmpty(property)) {
                    ConstraintDescriptor<?> constraintDescriptor = cv.getConstraintDescriptor();
                    Annotation annotation = constraintDescriptor.getAnnotation();
                    if (annotation instanceof NotAnyNull) {
                        NotAnyNull notAnyNull = (NotAnyNull) annotation;
                        StringJoiner joiner = new StringJoiner(",");
                        String[] fields = notAnyNull.fields();
                        for (String field : fields) {
                            joiner.add(field);
                        }
                        property = joiner.toString();
                    } else if (annotation instanceof PreNotNull) {
                        PreNotNull preNotNull = (PreNotNull) annotation;
                        StringJoiner joiner = new StringJoiner(",");
                        String[] fields = preNotNull.linkageFields();
                        for (String field : fields) {
                            joiner.add(field);
                        }
                        property = joiner.toString();
                    }
                }*/
                if (errorMap.get(property) != null) {
                    errorMap.get(property).append(",").append(cv.getMessage());
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }
}
