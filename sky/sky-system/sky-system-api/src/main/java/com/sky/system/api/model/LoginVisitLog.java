package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-12-14 014 13:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_login_visit_log")
public class LoginVisitLog extends BaseModel<LoginLog> {

    private String token;
    private String ip;
    private Integer needVerifyCode;
    private String verifyCode;
}
