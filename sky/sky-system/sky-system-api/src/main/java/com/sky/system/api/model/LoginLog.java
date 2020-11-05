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
    private String safetyCheckIp;
    private Date safetyCheckTime;
    private String safetyCheckValue;
    private String safetyCheckToken;
    private String verifyCodeIp;
    private Date verifyCodeTime;
    private String verifyCodeValue;
    private String verifyCodeType;
    private Long verifyCodeCount;
    private String verifyCodeToken;
    private String loginIp;
    private Date loginTime;
    private String loginCode;
    private String loginPassword;
    private String loginVerifyCode;
    private String loginSafetyCheckToken;
    private Long loginCount;
    private Long loginFailCount;
    private String loginResult;
    private String loginToken;
    private String logoutIp;
    private Date logoutTime;
}
