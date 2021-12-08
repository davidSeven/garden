package com.sky.framework.validator;

import com.sky.framework.validator.annotation.StringLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class StringLengthValidator implements ConstraintValidator<StringLength, Object> {

    private StringLength stringLength;

    @Override
    public void initialize(StringLength constraintAnnotation) {
        this.stringLength = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        if (null != stringLength) {
            int length = String.valueOf(value).length();
            int maxLen = stringLength.maxLength(),
                    minLen = stringLength.minLength();
            boolean b = length >= minLen && length <= maxLen;
            if (!b) {
                // 自定义提示
                context.disableDefaultConstraintViolation();
                String message = stringLength.message();
                context.buildConstraintViolationWithTemplate(message + ",最小长度:" + stringLength.minLength() + ",最大长度:" + stringLength.maxLength()).addConstraintViolation();
            }
            return b;
        }
        return false;
    }
}
