package com.stream.garden.system.role.vo;

import com.stream.garden.system.role.model.RoleFunction;

import java.io.Serializable;
import java.util.List;

/**
 * @author garden
 * @date 2019/7/22 22:55
 */
public class RoleFunctionVO implements Serializable {
    private static final long serialVersionUID = -5557847959632347025L;

    private String roleId;
    private List<RoleFunction> roleFunctionList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<RoleFunction> getRoleFunctionList() {
        return roleFunctionList;
    }

    public void setRoleFunctionList(List<RoleFunction> roleFunctionList) {
        this.roleFunctionList = roleFunctionList;
    }
}
