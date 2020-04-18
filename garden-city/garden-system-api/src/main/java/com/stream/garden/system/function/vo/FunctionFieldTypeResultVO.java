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

    // 角色设置权限时，判断是否选中
    private boolean checked;

    public FunctionFieldTypeResultVO() {}

    public FunctionFieldTypeResultVO(String functionId, String functionFieldId, Integer type, String name) {
        super(functionId, functionFieldId, type);
        this.name = name;
    }

    public FunctionFieldTypeResultVO(String functionId, String functionFieldId, Integer type, String name, boolean checked) {
        this(functionId, functionFieldId, type, name);
        this.checked = checked;
    }
}
