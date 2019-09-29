package com.stream.garden.framework.web.permission;

import com.stream.garden.framework.util.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限上下文信息
 *
 * @author garden
 * @date 2019-09-28 15:26
 */
public class PermissionContext {

    /**
     * 权限map
     */
    private static Map<String, String> PERMISSION_URL_MAP = new HashMap<>();

    /**
     * 加载权限url
     *
     * @param list list
     */
    public static void reloadPermissionUrl(List<Permission> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            for (Permission permission : list) {
                PERMISSION_URL_MAP.put(permission.getUrl(), permission.getCode());
            }
        }
    }

    /**
     * 清除权限url
     */
    public static void clear() {
        PERMISSION_URL_MAP.clear();
    }

    /**
     * 验证url是否需要权限
     *
     * @param url url
     * @return String
     */
    public static String getUrlCode(String url) {
        return PERMISSION_URL_MAP.get(url);
    }
}