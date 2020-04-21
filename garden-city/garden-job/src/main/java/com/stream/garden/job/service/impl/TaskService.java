package com.stream.garden.job.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.jdbc.util.SnowflakeIdWorker;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.job.config.JobScheduler;
import com.stream.garden.job.constants.JobConstant;
import com.stream.garden.job.dao.ITaskDao;
import com.stream.garden.job.exception.JobExceptionCode;
import com.stream.garden.job.model.Task;
import com.stream.garden.job.service.ITaskService;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author garden
 * @date 2019-10-23 11:01
 */
@Service
public class TaskService extends AbstractBaseService<Task, String, ITaskDao> implements ITaskService {

    @Override
    public int insert(Task task) throws ApplicationException {
        // 生成id
        task.setId(SnowflakeIdWorker.generateIdStr());
        // 默认启用
        task.setState(JobConstant.JOB_TASK_STATE_ENABLED);
        int result = super.insertSelective(task);
        if (result == 1) {
            // 新增任务默认启用，添加到job调度里面去
            try {
                JobScheduler.addJob(task.getId(), JobConstant.JOB_GROUP_DEFAULT, task.getCron(), task.getUrl(), task.getParams());
            } catch (SchedulerException e) {
                logger.error(e.getMessage(), e);
                throw new ApplicationException(JobExceptionCode.TASK_ADD_EXCEPTION);
            }
        }
        return result;
    }

    @Override
    public int update(Task task) throws ApplicationException {
        int result = super.updateSelective(task);
        if (result == 1) {
            // 先删除任务
            JobScheduler.deleteJob(task.getId(), JobConstant.JOB_GROUP_DEFAULT);
            if (JobConstant.JOB_TASK_STATE_ENABLED.equals(task.getState())) {
                // 如果任务是启用的，就创建任务
                try {
                    JobScheduler.addJob(task.getId(), JobConstant.JOB_GROUP_DEFAULT, task.getCron(), task.getUrl(), task.getParams());
                } catch (SchedulerException e) {
                    logger.error(e.getMessage(), e);
                    throw new ApplicationException(JobExceptionCode.TASK_EDIT_EXCEPTION);
                }
            }
        }
        return result;
    }

    @Override
    public void initJob() throws ApplicationException {
        try {
            // 查询启用的任务
            Task paramTask = new Task();
            paramTask.setState(JobConstant.JOB_TASK_STATE_ENABLED);
            List<Task> taskList = this.baseMapper.list(paramTask);
            if (CollectionUtil.isNotEmpty(taskList)) {
                for (Task task : taskList) {
                    // 添加任务
                    JobScheduler.addJob(task.getId(), JobConstant.JOB_GROUP_DEFAULT, task.getCron(), task.getUrl(), task.getParams());
                }
            }
            logger.debug("任务调度完成初始化");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(JobExceptionCode.TASK_INIT_EXCEPTION);
        }
    }

    @Override
    public void stateSwitch(Task task) throws ApplicationException {
        Task dbTask = this.get(task.getId());
        if (null == dbTask) {
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
        try {
            // 启用
            if (JobConstant.JOB_TASK_STATE_ENABLED.equals(task.getState())) {
                // 如果是禁用的，就启用
                if (JobConstant.JOB_TASK_STATE_DISABLED.equals(dbTask.getState())) {
                    Task updateTask = new Task();
                    updateTask.setId(dbTask.getId());
                    updateTask.setState(JobConstant.JOB_TASK_STATE_ENABLED);
                    super.updateSelective(updateTask);
                    JobScheduler.addJob(dbTask.getId(), JobConstant.JOB_GROUP_DEFAULT, dbTask.getCron(), dbTask.getUrl(), dbTask.getParams());
                }
            } else if (JobConstant.JOB_TASK_STATE_DISABLED.equals(task.getState())) {
                // 如果是启用的，就禁用
                if (JobConstant.JOB_TASK_STATE_ENABLED.equals(dbTask.getState())) {
                    Task updateTask = new Task();
                    updateTask.setId(dbTask.getId());
                    updateTask.setState(JobConstant.JOB_TASK_STATE_DISABLED);
                    super.updateSelective(updateTask);
                    JobScheduler.deleteJob(dbTask.getId(), JobConstant.JOB_GROUP_DEFAULT);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @Override
    public int delete(String... ids) throws ApplicationException {
        try {
            int i = 0;
            for (String id : ids) {
                Task task = super.get(id);
                if (null != task) {
                    i += super.delete(id);
                    JobScheduler.deleteJob(task.getId(), JobConstant.JOB_GROUP_DEFAULT);
                }
            }
            return i;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
