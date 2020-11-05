package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-11-05 005 14:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_login_log_token")
public class LoginLogToken extends Model<LoginLogToken> {

    /**
     * 检查token
     */
    private String safetyCheckToken;

    /**
     * 登录token
     */
    private String loginToken;

    /**
     * ID
     */
    private Long logId;
}
