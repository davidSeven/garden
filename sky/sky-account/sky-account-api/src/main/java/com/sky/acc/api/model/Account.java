package com.sky.acc.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("acc_account")
public class Account extends BaseModel<Account> {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "状态，激活：ACTIVATE，冻结：FREEZE，停用：DEACTIVATE")
    private String state;
}
