package com.sky.system.i18n;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class TestMessageSource {

    @Test
    public void testResourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n/messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setFallbackToSystemLocale(true);
        String message = resourceBundleMessageSource.getMessage("common.message", null, Locale.SIMPLIFIED_CHINESE);
        System.out.println(message);
        String message1 = resourceBundleMessageSource.getMessage("common.message", null, Locale.ENGLISH);
        System.out.println(message1);
        Object[] args = new Object[]{"参数信息"};
        String message2 = resourceBundleMessageSource.getMessage("common.message2", args, Locale.SIMPLIFIED_CHINESE);
        System.out.println(message2);

        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(resourceBundleMessageSource);
        validatorFactoryBean.afterPropertiesSet();
        Validator validator = validatorFactoryBean.getValidator();
        TestRequest testRequest = new TestRequest();
        testRequest.setAge(500);
        testRequest.setPhone("130123456789");
        testRequest.setPostCode("123456789");

        // 设置语言
        // LocaleContextMessageInterpolator
        // Validator验证的国际化在这个类的目录下面
        // PredefinedScopeHibernateValidatorFactory
        LocaleContextHolder.setLocale(Locale.ENGLISH);

        System.out.println(JSON.toJSONString(validate(testRequest, validator)));
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
