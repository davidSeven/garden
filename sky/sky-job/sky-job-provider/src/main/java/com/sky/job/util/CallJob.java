package com.sky.job.util;

import com.alibaba.fastjson.JSON;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.interceptor.util.ApplicationUtil;
import com.sky.job.api.JobInterface;
import com.sky.job.api.model.JobLog;
import com.sky.job.service.JobLogService;
import com.sky.job.service.JobService;
import org.quartz.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @date 2020-11-23 023 14:05
 */
public class CallJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(CallJob.class);

    private final JobService jobService;
    private final JobLogService jobLogService;
    private final RedissonClient redissonClient;
    private final RestTemplate restTemplate;

    public CallJob() {
        this.jobService = ApplicationUtil.getBeans(JobService.class);
        this.jobLogService = ApplicationUtil.getBeans(JobLogService.class);
        this.redissonClient = ApplicationUtil.getBeans(RedissonClient.class);
        this.restTemplate = ApplicationUtil.getBean("JobRestTemplate", RestTemplate.class);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取到taskId
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        // 创建日志对象
        JobLog jobLog = new JobLog();
        String jobId = jobKey.getName();
        jobLog.setTaskId(Long.valueOf(jobId));
        String key = JobInterface.SERVICE + ":Task:" + jobId;
        RLock lock = this.redissonClient.getLock(key);
        String response = null;
        // 开始时间
        Date processDate = new Date();
        jobLog.setBeginTime(processDate);
        try {
            if (lock.tryLock(10, TimeUnit.MINUTES)) {
                com.sky.job.api.model.Job sysJob = this.jobService.getById(jobId);
                this.jobService.begin(jobId, processDate);
                // 修改主任务上的相关信息
                String callUrl = sysJob.getTimingCallUrl();
                String httpMethod = sysJob.getHttpMethod();
                // retry
                if (null != sysJob.getHasRedo() && 1 == sysJob.getHasRedo()) {
                    response = this.retryServiceRequest(jobId, callUrl, httpMethod, 0, sysJob.getEndRedoTimes());
                } else {
                    response = this.serviceRequest(callUrl, httpMethod);
                }
                // 结束时间
                Date processEndDate = new Date();
                // 执行成功
                this.jobService.end(jobId, processDate, processEndDate, response);
                jobLog.setEndTime(processEndDate);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 保存响应内容
            response = e.getMessage();
            // 结束时间
            Date processEndDate = new Date();
            // 执行失败
            this.jobService.endByFailed(jobId, processDate, processEndDate, response);
            jobLog.setEndTime(processEndDate);
            throw new JobExecutionException(e.getMessage());
        } finally {
            lock.unlock();
            // 保存执行日志信息
            this.saveResponse(jobLog, response);
        }
    }

    /**
     * 服务请求
     *
     * @param callUrl    callUrl
     * @param httpMethod httpMethod
     * @return String
     */
    private String serviceRequest(String callUrl, String httpMethod) {
        HttpMethod hm = this.getHttpMethod(httpMethod);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=utf-8");
        HttpEntity<?> httpEntity = new HttpEntity<>("{}", headers);
        ResponseEntity<ResponseDto<?>> responseEntity = this.restTemplate.exchange(callUrl, hm, httpEntity, new DefaultTargetType<ResponseDto<?>>() {
        }.getClassType());
        ResponseDto<?> resultJson = responseEntity.getBody();
        if (null != resultJson) {
            return JSON.toJSONString(resultJson);
        }
        return null;
    }

    /**
     * 重试服务请求
     *
     * @param jobId       jobId
     * @param callUrl     callUrl
     * @param httpMethod  httpMethod
     * @param redoTime    redoTime
     * @param maxRedoTime maxRedoTime
     * @return String
     */
    private String retryServiceRequest(String jobId, String callUrl, String httpMethod, int redoTime, int maxRedoTime) {
        if (redoTime > maxRedoTime) {
            return null;
        }
        try {
            return this.serviceRequest(callUrl, httpMethod);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // retry
            redoTime++;
            if (redoTime <= maxRedoTime) {
                this.jobService.retry(jobId, redoTime, e.getMessage());
                // 15s后重试
                try {
                    Thread.sleep(15 * 1000L);
                } catch (InterruptedException ie) {
                    logger.error(ie.getMessage(), ie);
                }
                return this.retryServiceRequest(jobId, callUrl, httpMethod, redoTime, maxRedoTime);
            } else {
                // 异常抛出去
                throw e;
            }
        }
    }

    /**
     * getHttpMethod
     *
     * @param httpMethod httpMethod
     * @return HttpMethod
     */
    private HttpMethod getHttpMethod(String httpMethod) {
        HttpMethod hm = HttpMethod.GET;
        if (HttpMethod.POST.name().equals(httpMethod)) {
            hm = HttpMethod.POST;
        }
        return hm;
    }

    /**
     * 保存响应内容
     *
     * @param jobLog   日志
     * @param response 响应内容
     */
    private void saveResponse(JobLog jobLog, String response) {
        try {
            jobLog.setResponseMsg(response);
            jobLog.setConsumeTime(jobLog.getEndTime().getTime() - jobLog.getBeginTime().getTime());
            // 保存日志
            this.jobLogService.save(jobLog);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
