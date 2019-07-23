package com.stream.garden.system.role.model;

import com.stream.garden.framework.api.model.OrderBy;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019/7/22 22:27
 */
public class RoleFunction extends OrderBy implements Serializable {
    private static final long serialVersionUID = -3796085469093389903L;

    private String roleId;
    private String functionId;
    // 类型，1菜单，2功能
    private int type;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
