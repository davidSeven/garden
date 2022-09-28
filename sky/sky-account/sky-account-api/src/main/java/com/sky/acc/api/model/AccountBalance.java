package com.sky.acc.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("acc_account_balance")
public class AccountBalance extends BaseModel<AccountBalance> {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "币种")
    private String currency;

    @ApiModelProperty(value = "可用余额")
    private BigDecimal availableBalance;

    @ApiModelProperty(value = "冻结余额")
    private BigDecimal freezeBalance;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "信用余额")
    private BigDecimal creditBalance;

    @ApiModelProperty(value = "透支余额")
    private BigDecimal overdrawBalance;

}
