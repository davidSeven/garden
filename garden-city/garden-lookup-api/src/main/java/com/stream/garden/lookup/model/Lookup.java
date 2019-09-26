package com.stream.garden.lookup.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Lookup extends BaseModel<String> {
    private static final long serialVersionUID = 4684786300703845916L;

    private String name;
    private String code;
    private String state;
}
