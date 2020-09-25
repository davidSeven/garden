package com.user;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-09-22 022 14:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Model<User> {
    private static final long serialVersionUID = 1602139400880339940L;

    @ApiModelProperty(name = "帐号")
    private String account;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "状态")
    private String state;

    @ApiModelProperty(name = "密码")
    private String password;
}
