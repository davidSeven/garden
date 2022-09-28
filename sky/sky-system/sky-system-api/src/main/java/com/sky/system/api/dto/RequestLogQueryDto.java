package com.sky.system.api.dto;

import com.sky.framework.api.dto.AbstractQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "RequestLogQueryDto", description = "RequestLogQueryDto信息")
public class RequestLogQueryDto extends AbstractQueryDto {

    @ApiModelProperty(value = "日志ID")
    private String traceId;

    @ApiModelProperty(value = "跨度ID")
    private Long spanId;

    @ApiModelProperty(value = "请求域名")
    private String requestDomain;

    @ApiModelProperty(value = "请求路径")
    private String requestUri;

    @ApiModelProperty(value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(value = "请求ip")
    private String requestIp;
}
