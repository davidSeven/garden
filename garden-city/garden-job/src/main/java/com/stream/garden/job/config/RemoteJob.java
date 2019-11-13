package com.stream.garden.job.config;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.util.EncryptUtils;
import com.stream.garden.framework.util.HttpClientUtil;
import com.stream.garden.framework.web.util.ApplicationUtil;
import com.stream.garden.job.constants.JobConstant;
import com.stream.garden.job.model.TaskLog;
import com.stream.garden.job.service.ITaskLogService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 远程任务执行
 *
 * @author garden
 * @date 2019-10-23 11:25
 */
public class RemoteJob implements Job {
    private Logger logger = LoggerFactory.getLogger(RemoteJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取到taskId
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        // 创建日志对象
        TaskLog taskLog = new TaskLog();
        taskLog.setTaskId(jobKey.getName());
        taskLog.setBeginDate(new Date());
        try {
            JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
            String url = jobDataMap.getString(JobConstant.JOB_DATA_KEY_URL);
            String params = jobDataMap.getString(JobConstant.JOB_DATA_KEY_PARAMS);
            String response;
            Map<String, Object> headers = new HashMap<>();
            headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
            if (isRemote(params)) {
                response = HttpClientUtil.httpPostRequest(url, params, headers);
            } else {
                // 本地认证
                headers.put("remote-authorization", "1");
                Map<String, Object> tokenMap = new HashMap<>();
                tokenMap.put("time", System.currentTimeMillis());
                String tokenMapJson = JSONObject.toJSONString(tokenMap);
                String token = EncryptUtils.base64Encoder(tokenMapJson);
                headers.put("remote-authorization-token", token);
                response = HttpClientUtil.httpPostRequest(url, params, headers);
            }
            taskLog.setType(JobConstant.JOB_TASK_LOG_TYPE_SUCCESS);
            // 保存响应内容
            saveResponse(taskLog, response, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            taskLog.setType(JobConstant.JOB_TASK_LOG_TYPE_FAIL);
            // 保存响应内容
            saveResponse(taskLog, e.getMessage(), false);
            throw new JobExecutionException("调用远程服务异常");
        }
    }

    /**
     * 判断是否为远程服务
     *
     * @param params json字符串
     * @return boolean
     */
    private boolean isRemote(String params) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(params);
            // 判断isRemote参数是否为true
            if (null != jsonObject && jsonObject.getBooleanValue("isRemote")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 保存响应内容
     *
     * @param taskLog  日志
     * @param response 响应内容
     * @param success  是否成功
     */
    private void saveResponse(TaskLog taskLog, String response, boolean success) {
        try {
            taskLog.setEndDate(new Date());
            taskLog.setConsumeTime(countConsumeTime(taskLog));
            // 对response进行解析
            if (success) {
                taskLog.setResponseContent(substring(response));
            } else {
                taskLog.setResponseContent(substring(response));
            }
            ITaskLogService taskLogService = ApplicationUtil.getBeans(ITaskLogService.class);
            if (null != taskLogService) {
                // 保存日志
                taskLogService.insert(taskLog);
            } else {
                logger.error("未获取到ITaskLogService");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private long countConsumeTime(TaskLog taskLog) {
        Date beginDate = taskLog.getBeginDate();
        Date endDate = taskLog.getEndDate();
        return (endDate.getTime() - beginDate.getTime());
    }

    private String substring(String str) {
        if (str.length() > 500) {
            return str.substring(0, 500);
        }
        return str;
    }
}
