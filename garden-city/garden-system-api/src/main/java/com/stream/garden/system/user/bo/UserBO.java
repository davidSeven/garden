package com.stream.garden.system.user.bo;

import com.stream.garden.system.menu.vo.MenuVO;
import com.stream.garden.system.user.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

/**
 * @author garden
 * @date 2019-09-28 8:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBO extends User {
    private static final long serialVersionUID = 8464779045156592779L;

    // 角色名称
    private String roleName;
    // 角色状态
    private String roleState;
    // 用户菜单信息
    private List<MenuVO> menuList;
    // 权限集合
    private Set<String> permissionSet;
}
