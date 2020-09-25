package com.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-09-22 022 15:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "UserDto", description = "用户信息")
public class UserDto extends User {
    private static final long serialVersionUID = 7032468035722168680L;
}
