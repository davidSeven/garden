package com.stream.garden.framework.web.model;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-06-22 13:49
 */
public class Context {

    private Serializable user;

    public Serializable getUser() {
        return user;
    }

    public void setUser(Serializable user) {
        this.user = user;
    }
}
