package com.stream.garden.system.role.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author garden
 * @date 2019/8/25 20:19
 */
public class RoleMenuVO implements Serializable {
    private static final long serialVersionUID = -8694246183443752465L;

    private String roleId;
    private List<MenuFunctionVO> voList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<MenuFunctionVO> getVoList() {
        return voList;
    }

    public void setVoList(List<MenuFunctionVO> voList) {
        this.voList = voList;
    }
}
