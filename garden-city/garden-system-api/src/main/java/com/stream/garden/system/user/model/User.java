package com.stream.garden.system.user.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-06-19 11:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel<String> {
    private static final long serialVersionUID = -4012795609268802239L;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 状态
     */
    private String state;

    /**
     * 当前用户角色id
     */
    private String currentRoleId;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录失败次数
     * login_fail_lock_enable: true
     * login_fail_lock_count: 3
     */
    private Integer loginFailCount;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private String lastLoginDate;
}
