package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.OnlineUser;

/**
 * @date 2020-12-15 015 9:20
 */
public interface OnlineUserService extends IService<OnlineUser> {

    /**
     * 新增登录用户
     *
     * @param onlineUser onlineUser
     */
    void addOnlineUser(OnlineUser onlineUser);

    /**
     * 登出在线用户
     *
     * @param onlineUser onlineUser
     */
    void logoutOnlineUser(OnlineUser onlineUser);

    /**
     * 访问
     *
     * @param onlineUser onlineUser
     */
    void visitOnlineUser(OnlineUser onlineUser);
}
