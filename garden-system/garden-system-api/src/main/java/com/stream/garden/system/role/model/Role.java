package com.stream.garden.system.role.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 * @date 2019-06-22 11:01
 */
public class Role extends BaseModel<String> {
    private static final long serialVersionUID = 3029658396856331004L;

    private String name;
    private String code;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
