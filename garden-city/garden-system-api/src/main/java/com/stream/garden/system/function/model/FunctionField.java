package com.stream.garden.system.function.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2020-01-22 10:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FunctionField extends BaseModel<String> {
    private static final long serialVersionUID = 309810894391359449L;

    private String functionId;
    private String name;
    private String code;
    private String state;
    /**
     * 类型：10权限字段，20敏感字段
     */
    private String type;
}
