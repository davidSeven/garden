package com.sky.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.system.api.model.OnlineUser;

/**
 * @date 2020-12-15 015 9:20
 */
public interface OnlineUserDao extends BaseMapper<OnlineUser> {

    /**
     * 访问
     *
     * @param onlineUser onlineUser
     * @return int
     */
    int visitOnlineUser(OnlineUser onlineUser);
}
