package com.stream.garden.job.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.job.exception.JobExceptionCode;
import com.stream.garden.job.model.Task;
import com.stream.garden.job.model.TaskLog;
import com.stream.garden.job.service.ITaskLogService;
import com.stream.garden.job.service.ITaskService;
import com.stream.garden.job.vo.TaskLogVO;
import com.stream.garden.job.vo.TaskVO;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 */
@Controller
@RequestMapping("/job/taskLog")
public class TaskLogController {

    private Logger logger = LoggerFactory.getLogger(TaskLogController.class);

    @Autowired
    private ITaskLogService taskLogService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "task/logList";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<TaskLog>> pageList(TaskLogVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<TaskLog>>().setData(taskLogService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Task task) {
        try {
            return new Result<Integer>().ok().setData(taskLogService.delete(task.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

}
