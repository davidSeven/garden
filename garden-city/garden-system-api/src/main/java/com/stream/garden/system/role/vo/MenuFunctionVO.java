package com.stream.garden.system.role.vo;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019/7/23 22:31
 */
public class MenuFunctionVO implements Serializable {
    private static final long serialVersionUID = -5161122386648150723L;

    /**
     * 菜单
     */
    public static final int TYPE_MENU = 1;
    /**
     * 功能
     */
    public static final int TYPE_FUNCTION = 2;

    // menu or function
    private int type = TYPE_MENU;
    private String id;
    private String name;
    private String parentId;
    private boolean checked;

    public MenuFunctionVO() {
    }

    public MenuFunctionVO(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public MenuFunctionVO(String id, String name, String parentId, boolean checked) {
        this(id, name, parentId);
        this.checked = checked;
    }

    public MenuFunctionVO(int type, String id, String name, String parentId) {
        this(id, name, parentId);
        this.type = type;
    }

    public MenuFunctionVO(int type, String id, String name, String parentId, boolean checked) {
        this(type, id, name, parentId);
        this.checked = checked;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
