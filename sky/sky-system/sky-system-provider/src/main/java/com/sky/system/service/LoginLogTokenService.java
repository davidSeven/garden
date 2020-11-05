package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.LoginLogToken;

/**
 * @date 2020-11-05 005 14:27
 */
public interface LoginLogTokenService extends IService<LoginLogToken> {

    /**
     * 创建记录
     *
     * @param safetyCheckToken safetyCheckToken
     * @param logId            logId
     */
    void create(String safetyCheckToken, Long logId);

    /**
     * 修改token
     *
     * @param safetyCheckToken safetyCheckToken
     * @param loginToken       loginToken
     */
    void changeToken(String safetyCheckToken, String loginToken);

    /**
     * 查询ID
     *
     * @param safetyCheckToken safetyCheckToken
     * @return Long
     */
    Long getBySafetyCheckToken(String safetyCheckToken);

    /**
     * 查询ID
     *
     * @param loginToken loginToken
     * @return Long
     */
    Long getByLoginToken(String loginToken);
}
