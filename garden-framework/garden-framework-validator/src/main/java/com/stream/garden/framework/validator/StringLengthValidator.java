package com.stream.garden.framework.validator;

import com.stream.garden.framework.validator.annotation.StringLength;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
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
            if (length >= minLen && length <= maxLen) {
                return true;
            }
        }
        return false;
    }
}
