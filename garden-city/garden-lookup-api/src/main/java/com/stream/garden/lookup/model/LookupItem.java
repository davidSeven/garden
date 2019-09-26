package com.stream.garden.lookup.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LookupItem extends BaseModel<String > {
    private static final long serialVersionUID = 1335495282720827193L;

    private String name;
    private String code;
    private String state;

    private String parentCode;
    private String parentName;

    private String extendField1;
    private String extendField2;
    private String extendField3;
    private String extendField4;

}
