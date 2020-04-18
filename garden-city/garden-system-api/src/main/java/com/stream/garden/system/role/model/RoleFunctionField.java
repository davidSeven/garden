package com.stream.garden.system.role.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author garden
 * @date 2020/04/18
 */
@Data
public class RoleFunctionField implements Serializable {
    private static final long serialVersionUID = -3796085469093389903L;

    private String roleId;
    private String functionId;
    private String functionFieldId;
    // 类型，1菜单，2功能
    private Integer type;

}
