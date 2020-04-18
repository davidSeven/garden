package com.stream.garden.system.role.model;

import com.stream.garden.framework.api.model.OrderBy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author garden
 * @date 2020/04/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleFunctionField extends OrderBy implements Serializable {
    private static final long serialVersionUID = -3796085469093389903L;

    private String roleId;
    private String functionId;
    private String functionFieldId;
    // 类型，1菜单，2功能
    private Integer type;

}
