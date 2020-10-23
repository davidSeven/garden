package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2020-10-23 023 13:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_t")
public class User extends BaseModel<User> {

    private String code;
}
