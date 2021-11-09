package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.system.api.dto.SerialNumberQueryDto;
import com.sky.system.api.enums.SerialNumberTypeEnum;
import com.sky.system.api.model.SerialNumber;
import com.sky.system.dao.SerialNumberDao;
import com.sky.system.service.SerialNumberService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
public class SerialNumberServiceImpl extends ServiceImpl<SerialNumberDao, SerialNumber> implements SerialNumberService {

    private static final ConcurrentMap<String, MessageFormat> MSG_FORMAT_MAP = new ConcurrentHashMap<>();

    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public IPage<SerialNumber> page(SerialNumberQueryDto dto) {
        return null;
    }

    @Override
    public String generateNumber(String code) {
        List<String> list = this.generateNumbers(code, 1);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<String> generateNumbers(String code, int num) {
        // code不能为空
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        // key
        String key = applicationName + ":serialnumber:" + code;
        RLock lock = redissonClient.getLock(key);
        // time 5 seconds
        long time = 5;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        SerialNumber serialNumber = null;
        try {
            if (lock.tryLock(time, timeUnit)) {
                serialNumber = this.updateSerialNumber(code, num);
            }
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            lock.unlock();
        }
        return this.getSerialNumbers(serialNumber, num);
    }

    private SerialNumber updateSerialNumber(String code, int num) {
        // 查询流水号
        QueryWrapper<SerialNumber> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        SerialNumber serialNumber = super.getOne(queryWrapper);
        if (null == serialNumber) {
            throw new CommonException(500, "业务编码[" + code + "]未配置");
        }
        // 修改流水号相关信息
        SerialNumber updateSerialNumber = new SerialNumber();
        updateSerialNumber.setId(serialNumber.getId());
        if (serialNumber.getType() == SerialNumberTypeEnum.CYCLE.ordinal()) {
            Date currentDate = new Date();
            String currentCycle = DateFormatUtils.format(currentDate, serialNumber.getCycleFormat());
            if (!currentCycle.equals(serialNumber.getCurrentCycle())) {
                serialNumber.setCurrentSequence(0L);
                serialNumber.setCurrentCycle(currentCycle);
                // 更新当前序列号，当前周期
                updateSerialNumber.setCurrentSequence(0L);
                updateSerialNumber.setCurrentCycle(currentCycle);
            }
        }
        int step = serialNumber.getStep();
        long end = serialNumber.getCurrentSequence() + step * num;
        // 更新当前序列号
        updateSerialNumber.setCurrentSequence(end);
        // 更新
        super.updateById(updateSerialNumber);
        return serialNumber;
    }

    private List<String> getSerialNumbers(SerialNumber serialNumber, int num) {
        if (null == serialNumber) {
            return null;
        }
        String code = serialNumber.getCode();
        int step = serialNumber.getStep();
        // 处理流水号
        List<String> numberList = new ArrayList<>();
        // 缓存MessageFormat
        MessageFormat messageFormat = MSG_FORMAT_MAP.get(code);
        if (null == messageFormat) {
            messageFormat = new MessageFormat(serialNumber.getTemplate());
            MSG_FORMAT_MAP.put(code, messageFormat);
        }
        long start = serialNumber.getCurrentSequence();
        for (int i = 0; i < num; i++) {
            start += step;
            numberList.add(formatNumber(messageFormat, serialNumber, start));
        }
        return numberList;
    }

    /**
     * 格式化流水号
     *
     * @param messageFormat messageFormat
     * @param serialNumber  serialNumber
     * @param start         start
     * @return string
     */
    private String formatNumber(MessageFormat messageFormat, SerialNumber serialNumber, long start) {
        // 周期 + 增长值
        Object[] formatArgs;
        // 填充字符串
        String fullChar = "0";
        String fullBegin = fullBegin(String.valueOf(start), serialNumber.getLength(), fullChar);
        if (serialNumber.getType() == SerialNumberTypeEnum.CYCLE.ordinal()) {
            formatArgs = new String[2];
            formatArgs[0] = serialNumber.getCurrentCycle();
            formatArgs[1] = fullBegin;
        } else {
            formatArgs = new String[1];
            formatArgs[0] = fullBegin;
        }
        // 处理前缀
        String prefix = serialNumber.getPrefix();
        if (StringUtils.isEmpty(prefix)) {
            prefix = "";
        }
        return prefix + messageFormat.format(formatArgs);
    }

    /**
     * 字符串填充头部
     *
     * @param str 字符串
     * @return string
     */
    private String fullBegin(String str, int len, String fullChar) {
        int n = len - str.length();
        if (n > 0) {
            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < n; i++) {
                builder.append(fullChar);
            }
            builder.append(str);
            return builder.toString();
        }
        return str;
    }
}
