package com.sky.framework.validator;

import com.sky.framework.validator.annotation.StringLength;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class DemoUser {

    @StringLength(message = "名称长度不正确")
    public String name;

    @Size(message = "长度不正确", min = 5, max = 10)
    public String hobby;
}
