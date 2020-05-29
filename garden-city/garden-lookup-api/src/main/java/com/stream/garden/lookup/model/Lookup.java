package com.stream.garden.lookup.model;

import com.stream.garden.framework.api.model.BaseModel;
import com.stream.garden.framework.validator.groups.ValidationSaveGroup;
import com.stream.garden.framework.validator.groups.ValidationUpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author garden
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Lookup extends BaseModel<String> {
    private static final long serialVersionUID = 4684786300703845916L;

    @NotEmpty(groups = {ValidationSaveGroup.class, ValidationUpdateGroup.class})
    private String name;

    @NotEmpty(groups = {ValidationSaveGroup.class, ValidationUpdateGroup.class})
    private String code;

    private String state;
}
