package com.sky.del.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDeleteDto {

    @NotNull(message = "出库单号不能为空")
    @ApiModelProperty(value = "出库单号")
    private List<String> orderNos;
}
