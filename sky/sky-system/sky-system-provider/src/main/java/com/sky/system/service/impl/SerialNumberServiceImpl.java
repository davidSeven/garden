package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.system.api.dto.SerialNumberQueryDto;
import com.sky.system.api.enums.SerialNumberTypeEnum;
import com.sky.system.api.model.SerialNumber;
import com.sky.system.dao.SerialNumberDao;
import com.sky.system.document.DocumentSerialNumber;
import com.sky.system.service.MongoSerialNumberService;
import com.sky.system.service.SerialNumberService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
// @CacheConfig(cacheManager = "defaultCacheManager")
public class SerialNumberServiceImpl extends ServiceImpl<SerialNumberDao, SerialNumber> implements SerialNumberService, KeyGenerator {
    private final Logger logger = LoggerFactory.getLogger(SerialNumberServiceImpl.class);
    private static final ConcurrentMap<String, MessageFormat> MSG_FORMAT_MAP = new ConcurrentHashMap<>();

    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private MongoSerialNumberService mongoSerialNumberService;

    @Override
    public IPage<SerialNumber> page(SerialNumberQueryDto dto) {
        IPage<SerialNumber> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<SerialNumber> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(dto.getCode())) {
            queryWrapper.eq(SerialNumber::getCode, dto.getCode());
        }
        if (null != dto.getType()) {
            queryWrapper.eq(SerialNumber::getType, dto.getType());
        }
        return super.page(iPage, queryWrapper);
    }

    // @Cacheable(value = {SystemInterface.SERVICE + ":SerialNumber"}, key = "#id", condition = "#id != null")
    @Override
    public SerialNumber getById(Serializable id) {
        return super.getById(id);
    }

    // @CacheEvict(value = {SystemInterface.SERVICE + ":SerialNumber"}, key = "#entity.code", condition = "#entity.code != null")
    @Override
    public boolean updateById(SerialNumber entity) {
        // ????????????
        // key
        String code = entity.getCode();
        String key = applicationName + ":serialnumber:" + code;
        RLock lock = redissonClient.getLock(key);
        // time 5 seconds
        long time = 5;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        try {
            if (lock.tryLock(time, timeUnit)) {
                // ??????mongodb???????????????
                DocumentSerialNumber documentSerialNumber = mongoSerialNumberService.getById(entity.getId());
                if (null != documentSerialNumber) {
                    entity.setCurrentSequence(documentSerialNumber.getCurrentSequence());
                    entity.setCurrentCycle(documentSerialNumber.getCurrentCycle());
                    // ??????mongodb?????????
                    mongoSerialNumberService.removeById(entity.getId());
                }
                return super.updateById(entity);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
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
        // code????????????
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

    @Transactional
    @Override
    public int asyncData() {
        List<DocumentSerialNumber> documentSerialNumberList = mongoSerialNumberService.findAll();
        int updateCount = 0;
        if (CollectionUtils.isNotEmpty(documentSerialNumberList)) {
            for (DocumentSerialNumber documentSerialNumber : documentSerialNumberList) {
                SerialNumber updateSerialNumber = new SerialNumber();
                updateSerialNumber.setId(documentSerialNumber.getId());
                updateSerialNumber.setCurrentSequence(documentSerialNumber.getCurrentSequence());
                updateSerialNumber.setCurrentCycle(documentSerialNumber.getCurrentCycle());
                updateCount += super.baseMapper.updateById(updateSerialNumber);
            }
        }
        return updateCount;
    }

    private SerialNumber updateSerialNumber(String code, int num) {
        // ???????????????
        QueryWrapper<SerialNumber> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        SerialNumber serialNumber = super.getOne(queryWrapper);
        if (null == serialNumber) {
            throw new CommonException(500, "????????????[" + code + "]?????????");
        }
        // ???????????????????????????
        SerialNumber updateSerialNumber = new SerialNumber();
        updateSerialNumber.setId(serialNumber.getId());
        if (serialNumber.getType() == SerialNumberTypeEnum.CYCLE.ordinal()) {
            Date currentDate = new Date();
            String currentCycle = DateFormatUtils.format(currentDate, serialNumber.getCycleFormat());
            if (!currentCycle.equals(serialNumber.getCurrentCycle())) {
                serialNumber.setCurrentSequence(0L);
                serialNumber.setCurrentCycle(currentCycle);
                // ????????????????????????????????????
                updateSerialNumber.setCurrentSequence(0L);
                updateSerialNumber.setCurrentCycle(currentCycle);
            }
        }
        int step = serialNumber.getStep();
        long end = serialNumber.getCurrentSequence() + step * num;
        // ?????????????????????
        updateSerialNumber.setCurrentSequence(end);
        // ??????
        super.updateById(updateSerialNumber);
        return serialNumber;
    }

    private List<String> getSerialNumbers(SerialNumber serialNumber, int num) {
        if (null == serialNumber) {
            return null;
        }
        String code = serialNumber.getCode();
        int step = serialNumber.getStep();
        // ???????????????
        List<String> numberList = new ArrayList<>();
        // ??????MessageFormat
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
     * ??????????????????
     *
     * @param messageFormat messageFormat
     * @param serialNumber  serialNumber
     * @param start         start
     * @return string
     */
    private String formatNumber(MessageFormat messageFormat, SerialNumber serialNumber, long start) {
        // ?????? + ?????????
        Object[] formatArgs;
        // ???????????????
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
        // ????????????
        String prefix = serialNumber.getPrefix();
        if (StringUtils.isEmpty(prefix)) {
            prefix = "";
        }
        return prefix + messageFormat.format(formatArgs);
    }

    /**
     * ?????????????????????
     *
     * @param str ?????????
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

    @Override
    @NonNull
    public Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {

        return null;
    }
}
