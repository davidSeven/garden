package com.stream.garden.system.group.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 * @date 2019-06-22 11:03
 */
public class Group extends BaseModel<String> {
    private static final long serialVersionUID = 8028185610095232591L;

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
