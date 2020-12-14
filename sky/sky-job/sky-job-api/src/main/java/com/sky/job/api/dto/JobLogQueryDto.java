package com.sky.job.api.dto;

import com.sky.job.api.model.JobLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @date 2020-06-09 11:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel(value = "JobLogQueryDto", description = "任务日志查询信息")
public class JobLogQueryDto extends JobLog {

    @ApiModelProperty("当前页，从1开始，默认为1")
    private int pageNum = 1;

    @ApiModelProperty("每页的数量，默认为10")
    private int pageSize = 10;
}
