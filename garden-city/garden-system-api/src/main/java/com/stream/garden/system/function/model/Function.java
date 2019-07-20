package com.stream.garden.system.function.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 * @date 2019-07-20 15:35
 */
public class Function extends BaseModel<String> {
    private static final long serialVersionUID = -5957528363925004048L;

    private String menuId;
    private String name;
    private String code;
    private String state;
    private String url;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
