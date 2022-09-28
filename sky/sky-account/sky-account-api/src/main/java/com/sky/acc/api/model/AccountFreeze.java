package com.sky.acc.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("acc_account_freeze")
public class AccountFreeze extends BaseModel<AccountFreeze> {

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "币种")
    private String currency;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "单据类型")
    private String invoiceType;

    @ApiModelProperty(value = "单据号")
    private String invoiceNo;

    @ApiModelProperty(value = "单据行号")
    private String invoiceLineNo;

}
