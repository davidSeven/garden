package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @date 2020-11-05 005 14:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_login_log")
public class LoginLog extends BaseModel<LoginLog> {

    private String state;
    private String loginIp;
    private Date loginTime;
    private String loginCode;
    private String loginPassword;
    private String loginVerifyCode;
    private String loginToken;
}
