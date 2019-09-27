package com.stream.garden.system.user.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

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
     * CURRENT_ROLE_ID
     */
    private String currentRoleId;

    /**
     * 密码
     * PASSWORD
     */
    private String password;

    /**
     * 登录失败次数
     * login_fail_lock_enable: true
     * login_fail_lock_count: 3
     * LOGIN_FAIL_COUNT
     */
    private Integer loginFailCount;

    /**
     * 最后登录ip
     * LAST_LOGIN_IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     * LAST_LOGIN_DATE
     */
    private Timestamp lastLoginDate;

    /**
     * 文件业务编码-头像
     */
    private String bizCode;

    /**
     * 文件业务ID-头像
     */
    private String bizId;

    /**
     * 头像地址
     */
    private String bizHeadPath;
}
