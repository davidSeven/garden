package com.stream.garden.system.function.vo;

import com.stream.garden.system.function.model.FunctionFieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2020-04-08 19:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FunctionFieldTypeResultVO extends FunctionFieldType {
    private static final long serialVersionUID = -2749318502104946411L;

    private String name;

    public FunctionFieldTypeResultVO() {}

    public FunctionFieldTypeResultVO(String functionId, String functionFieldId, Integer type, String name) {
        super(functionId, functionFieldId, type);
        this.name = name;
    }
}
