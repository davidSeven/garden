package com.stream.garden.excel.controller;

import com.stream.garden.excel.exception.ExcelExceptionCode;
import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.excel.service.IExcelConfigService;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.framework.cache.util.RedisLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author garden
 * @date 2019-11-04 16:21
 */
@Controller
@RequestMapping(value = "/excel/config")
public class ExcelConfigController {
    private Logger logger = LoggerFactory.getLogger(ExcelConfigController.class);

    private IExcelConfigService excelConfigService;

    @Autowired
    public ExcelConfigController(IExcelConfigService excelConfigService) {
        this.excelConfigService = excelConfigService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "excel/config/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "excel/config/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(ExcelConfig excelConfig) {
        try {
            return new Result<Integer>().ok().setData(excelConfigService.insert(excelConfig));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ExcelExceptionCode.EXCEL_CONFIG_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(ExcelConfig excelConfig) {
        try {
            return new Result<Integer>().ok().setData(excelConfigService.update(excelConfig));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ExcelExceptionCode.EXCEL_CONFIG_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<ExcelConfig> get(ExcelConfig excelConfig) {
        try {
            return new Result<ExcelConfig>().ok().setData(excelConfigService.get(excelConfig.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/list")
    @ResponseBody
    public Result<List<ExcelConfig>> list(ExcelConfig excelConfig) {
        try {
            String key = "test:1";
            String value = "" + System.currentTimeMillis();
            int expireTime = 5;
            int waitTime = 3000;
            if (RedisLockUtil.getSpinLock(key, value, expireTime, waitTime)) {
                // 守护进程
                Timer timer = new Timer();
                // 每隔1秒，重新设置一次redid key的有效时间
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        RedisLockUtil.expire(key, 5, TimeUnit.SECONDS);
                    }
                }, 3000, 3000);
                // 10s
                // Thread.sleep(10 * 1000);
                excelConfig.asOrderBy("SORTS", OrderByObj.ASC);
                List<ExcelConfig> list = this.excelConfigService.list(excelConfig);
                timer.cancel();
                RedisLockUtil.releaseLock(key, value);
                return new Result<List<ExcelConfig>>().ok().setData(list);
            }
            throw new ApplicationException(ExceptionCode.TIME_OUT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(ExcelConfig excelConfig) {
        try {
            return new Result<Integer>().ok().setData(excelConfigService.delete(excelConfig.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
