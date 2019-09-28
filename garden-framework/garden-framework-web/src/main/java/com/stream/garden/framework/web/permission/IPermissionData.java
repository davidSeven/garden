package com.stream.garden.framework.web.permission;

import java.util.List;

/**
 * @author garden
 * @date 2019-09-28 15:24
 */
public interface IPermissionData {

    /**
     * 获取系统所有的权限路径
     *
     * @return list
     */
    List<Permission> getSystemPermissionList();

    /**
     * 验证url权限
     *
     * @param urlCode urlCode
     * @return boolean
     */
    boolean validPermission(String urlCode);
}
