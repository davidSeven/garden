package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @date 2020-12-15 015 9:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_online_user")
public class OnlineUser extends BaseModel<OnlineUser> {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "登录token")
    private String loginToken;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    private Date loginDate;

    @ApiModelProperty(value = "最后访问时间")
    private Date lastVisitDate;

    @ApiModelProperty(value = "租期")
    private Long leaseTime;

    @ApiModelProperty(value = "过期时间")
    private Date expireDate;

    @ApiModelProperty(value = "登出ip")
    private String logoutIp;

    @ApiModelProperty(value = "登出时间")
    private Date logoutDate;

    @ApiModelProperty(value = "状态")
    private String state;
}
