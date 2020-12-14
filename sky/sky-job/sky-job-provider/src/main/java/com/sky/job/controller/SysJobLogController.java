package com.sky.job.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.job.api.dto.JobLogQueryDto;
import com.sky.job.api.model.JobLog;
import com.sky.job.service.JobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"任务调度日志"})
@RestController
@RequestMapping("/job-log")
public class SysJobLogController {

    @Autowired
    private JobLogService jobLogService;

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "queryDto", value = "查询对象", dataType = "JobLogQueryDto")
    @PostMapping(value = "/page")
    public ResponseDto<IPage<JobLog>> pageList(@RequestBody JobLogQueryDto queryDto) {
        IPage<JobLog> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        QueryWrapper<JobLog> queryWrapper = new QueryWrapper<>(queryDto);
        return new ResponseDto<IPage<JobLog>>().ok().setData(this.jobLogService.page(page, queryWrapper));
    }

    @ApiOperation(value = "清理日志")
    @ApiImplicitParam(name = "days", value = "清理多少天之前的日志")
    @GetMapping("/clearLogs/{days}")
    public ResponseDto<Integer> clearLogs(@PathVariable("days") Integer days) {
        if (null == days) {
            days = 0;
        }
        return new ResponseDto<Integer>().ok().setData(this.jobLogService.clearLogs(days));
    }
}
