package com.stream.garden.system.menu.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 */
public class Menu extends BaseModel<String> {
    private static final long serialVersionUID = 154856706753265388L;

    private String name;
    private String code;
    private String state;

    private String path;
    private Integer sorts;
    private String type;

    private String parentId;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
