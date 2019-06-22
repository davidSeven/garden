package com.stream.garden.system.role.model;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-06-22 11:02
 */
public class RoleGroup implements Serializable {
    private static final long serialVersionUID = 5328934711176060068L;

    private String roleId;
    private String groupId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
