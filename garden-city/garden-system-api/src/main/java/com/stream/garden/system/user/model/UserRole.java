package com.stream.garden.system.user.model;

import com.stream.garden.framework.api.model.OrderBy;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-06-22 10:52
 */
public class UserRole extends OrderBy implements Serializable{
    private static final long serialVersionUID = -3362243351584350170L;

    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
