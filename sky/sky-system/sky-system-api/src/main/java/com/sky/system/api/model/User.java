package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.dao.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @date 2020-10-23 023 13:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_t")
public class User extends BaseModel<User> {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录失败次数")
    private Integer loginFailCount;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    private Date loginDate;
}
