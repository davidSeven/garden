package com.stream.garden.system.number.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.cache.util.RedissLockUtil;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.number.dao.ISerialNumberDao;
import com.stream.garden.system.number.enums.SerialNumberTypeEnum;
import com.stream.garden.system.number.model.SerialNumber;
import com.stream.garden.system.number.service.ISerialNumberService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author garden
 * @date 2020-06-04 17:18
 */
@Service
public class SerialNumberImpl extends AbstractBaseService<SerialNumber, ISerialNumberDao> implements ISerialNumberService {

    private static final ConcurrentMap<String, MessageFormat> msgFormatMap = new ConcurrentHashMap<>();

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String generateNumber(String code) throws ApplicationException {
        List<String> list = this.generateNumbers(code, 1);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<String> generateNumbers(String code, int num) throws ApplicationException {
        // code不能为空
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        // lockKey
        String lockKey = applicationName + ":serialnumber:" + code;
        // time 5 seconds
        int time = 5;
        SerialNumber serialNumber = null;
        try {
            if (RedissLockUtil.tryLock(lockKey, time, time)) {
                serialNumber = this.updateSerialNumber(code, num);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
        return this.getSerialNumbers(serialNumber, num);
    }

    private SerialNumber updateSerialNumber(String code, int num) throws ApplicationException {
        // 查询流水号
        SerialNumber serialNumber = super.baseMapper.getByCode(code);
        if (null == serialNumber) {
            return null;
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
        super.updateSelective(updateSerialNumber);
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
        MessageFormat messageFormat = msgFormatMap.get(code);
        if (null == messageFormat) {
            messageFormat = new MessageFormat(serialNumber.getTemplate());
            msgFormatMap.put(code, messageFormat);
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
