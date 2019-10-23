package com.stream.garden.job.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.job.exception.JobExceptionCode;
import com.stream.garden.job.model.Task;
import com.stream.garden.job.service.ITaskService;
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
@RequestMapping("/job/task")
public class TaskController {

    private Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ITaskService taskService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "task/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "task/edit";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<Task>> pageList(TaskVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<Task>>().setData(taskService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Task task) {
        try {
            // 验证cron
            if (!CronExpression.isValidExpression(task.getCron())) {
                return new Result<>(JobExceptionCode.TASK_CRON_EXCEPTION);
            }
            return new Result<Integer>().setData(taskService.insert(task)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, JobExceptionCode.TASK_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Task task) {
        try {
            // 验证cron
            if (!CronExpression.isValidExpression(task.getCron())) {
                return new Result<>(JobExceptionCode.TASK_CRON_EXCEPTION);
            }
            return new Result<Integer>().ok().setData(taskService.update(task));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, JobExceptionCode.TASK_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Task task) {
        try {
            return new Result<Integer>().ok().setData(taskService.delete(task.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/job")
    @ResponseBody
    public Result job(Task task) {
        try {
            return new Result().ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
