package com.stream.garden.system.menu.model;

import com.stream.garden.framework.api.model.BaseModel;

/**
 * @author garden
 */
public class Menu extends BaseModel<String> {
    private static final long serialVersionUID = 154856706753265388L;

    /** 菜单名称 */
    private String name;
    /** 菜单编号 */
    private String code;
    /** 菜单状态 */
    private String state;

    /** 菜单地址 */
    private String path;
    /** 菜单顺序 */
    private Integer sorts;
    /** 菜单类型 */
    private String type;

    /** 菜单父级id */
    private String parentId;
    /** 菜单图标 */
    private String icon;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
