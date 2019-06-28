package com.stream.garden.system.user.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 * @date 2019-06-19 11:19
 */
public class User extends BaseModel<String> {
    private static final long serialVersionUID = -4012795609268802239L;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 状态
     */
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
