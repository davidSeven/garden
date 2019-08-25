package com.stream.garden.system.role.model;

import com.stream.garden.framework.api.model.OrderBy;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019/8/25 20:16
 */
public class RoleMenu extends OrderBy implements Serializable {
    private static final long serialVersionUID = -4159933165772708393L;

    private String roleId;
    private String menuId;

    public RoleMenu() {
    }

    public RoleMenu(String roleId) {
        this.roleId = roleId;
    }

    public RoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
