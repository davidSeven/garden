package com.sky.job.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.job.api.dto.JobQueryDto;
import com.sky.job.api.model.Job;
import com.sky.job.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"任务调度"})
@RestController
@RequestMapping("/job")
public class SysJobController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "job", value = "任务信息", dataType = "Job")
    @PostMapping("/save")
    public ResponseDto<Boolean> save(@RequestBody Job job) {
        return new ResponseDto<Boolean>().ok().setData(this.jobService.save(job));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "job", value = "任务信息", dataType = "Job")
    @PostMapping("/update")
    public ResponseDto<Boolean> update(@RequestBody Job job) {
        return new ResponseDto<Boolean>().ok().setData(this.jobService.updateById(job));
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "job", value = "任务信息", dataType = "Job")
    @PostMapping("/delete")
    public ResponseDto<Boolean> delete(@RequestBody Job job) {
        return new ResponseDto<Boolean>().ok().setData(this.jobService.removeById(job.getId()));
    }

    @ApiOperation(value = "启用")
    @ApiImplicitParam(name = "dto", value = "任务信息", dataType = "Job")
    @PostMapping("/enabled")
    public ResponseDto<Boolean> enabled(@RequestBody Job dto) {
        return new ResponseDto<Boolean>().ok().setData(this.jobService.enabled(dto.getId()));
    }

    @ApiOperation(value = "禁用")
    @ApiImplicitParam(name = "dto", value = "任务信息", dataType = "Job")
    @PostMapping("/disabled")
    public ResponseDto<Boolean> disabled(@RequestBody Job dto) {
        return new ResponseDto<Boolean>().ok().setData(this.jobService.disabled(dto.getId()));
    }

    @ApiOperation(value = "触发任务")
    @ApiImplicitParam(name = "dto", value = "任务信息", dataType = "Job")
    @PostMapping("/trigger")
    public ResponseDto<Boolean> trigger(@RequestBody Job dto) {
        return new ResponseDto<Boolean>().ok().setData(this.jobService.trigger(dto.getId()));
    }

    @ApiOperation(value = "查看")
    @ApiImplicitParam(name = "dto", value = "任务信息", dataType = "Job")
    @PostMapping("/get")
    public ResponseDto<Job> get(@RequestBody Job dto) {
        return new ResponseDto<Job>().ok().setData(this.jobService.getById(dto.getId()));
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "queryDto", value = "查询对象", dataType = "JobQueryDto")
    @PostMapping(value = "/page")
    public ResponseDto<IPage<Job>> pageList(@RequestBody JobQueryDto queryDto) {
        IPage<Job> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        LambdaQueryWrapper<Job> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(queryDto.getTaskName())) {
            queryWrapper.eq(Job::getTaskName, queryDto.getTaskName().trim());
        }
        return new ResponseDto<IPage<Job>>().ok().setData(this.jobService.page(page, queryWrapper));
    }
}
