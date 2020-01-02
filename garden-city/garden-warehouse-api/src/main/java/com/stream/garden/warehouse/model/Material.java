package com.stream.garden.warehouse.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-12-29 15:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Material extends BaseModel<String> {
    private static final long serialVersionUID = -2303849705691902238L;

    private String name;
    private String code;
    private String state;
}
