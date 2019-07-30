package com.stream.garden.lookup.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 */
public class Lookup extends BaseModel<String> {
    private static final long serialVersionUID = 4684786300703845916L;

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
