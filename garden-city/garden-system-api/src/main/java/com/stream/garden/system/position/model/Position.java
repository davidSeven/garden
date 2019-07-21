package com.stream.garden.system.position.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 * @date 2019/7/21 12:23
 */
public class Position extends BaseModel<String> {
    private static final long serialVersionUID = -1398483831169934816L;

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
