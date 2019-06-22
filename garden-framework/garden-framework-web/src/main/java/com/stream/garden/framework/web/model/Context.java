package com.stream.garden.framework.web.model;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-06-22 13:49
 */
public class Context {

    private String userName;
    private String userId;
    private String roleId;

    private Serializable user;

    public Serializable getUser() {
        return user;
    }

    public void setUser(Serializable user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
