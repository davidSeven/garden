package com.sky.system.i18n;

import com.sky.framework.validator.annotation.StringLength;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TestRequest {

    @NotBlank(message = "{common.notBlank}")
    private String name;

    @Min(value = 0)
    @Max(value = 200)
    private Integer age;

    @StringLength(minLength = 11, maxLength = 11, message = "{common.stringLength}")
    private String phone;

    @Size(max = 6)
    private String postCode;
}
