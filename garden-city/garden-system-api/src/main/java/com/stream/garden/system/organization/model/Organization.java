package com.stream.garden.system.organization.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 * @date 2019/7/21 11:30
 */
public class Organization extends BaseModel<String> {
    private static final long serialVersionUID = -6689365956228876667L;

    private String name;
    private String code;
    private String state;

    private Integer sorts;
    /** 父级id */
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

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
