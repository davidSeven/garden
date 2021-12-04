package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mongodb.client.result.DeleteResult;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.enums.SerialNumberTypeEnum;
import com.sky.system.api.model.SerialNumber;
import com.sky.system.document.DocumentSerialNumber;
import com.sky.system.service.MongoSerialNumberService;
import com.sky.system.service.SerialNumberService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
public class MongoSerialNumberServiceImpl implements MongoSerialNumberService {
    private final Logger logger = LoggerFactory.getLogger(MongoSerialNumberServiceImpl.class);
    private static final ConcurrentMap<String, MessageFormat> MSG_FORMAT_MAP = new ConcurrentHashMap<>();
    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private SerialNumberService serialNumberService;

    @Override
    public String generateNumber(String code) {
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
        DocumentSerialNumber documentSerialNumber = null;
        try {
            if (lock.tryLock(time, timeUnit)) {
                Query query = new Query(Criteria.where("code").is(code));
                documentSerialNumber = mongoTemplate.findOne(query, DocumentSerialNumber.class, "serialNumber");
                if (null == documentSerialNumber) {
                    QueryWrapper<SerialNumber> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("code", code);
                    SerialNumber serialNumber = serialNumberService.getOne(queryWrapper);
                    if (null == serialNumber) {
                        throw new CommonException(500, "业务编码[" + code + "]未配置");
                    }
                    documentSerialNumber = BeanHelpUtil.convertDto(serialNumber, DocumentSerialNumber.class);
                    mongoTemplate.insert(documentSerialNumber, "serialNumber");
                }
                // 修改流水号相关信息
                Update updateSerialNumber = new Update();
                if (documentSerialNumber.getType() == SerialNumberTypeEnum.CYCLE.ordinal()) {
                    Date currentDate = new Date();
                    String currentCycle = DateFormatUtils.format(currentDate, documentSerialNumber.getCycleFormat());
                    if (!currentCycle.equals(documentSerialNumber.getCurrentCycle())) {
                        documentSerialNumber.setCurrentSequence(0L);
                        documentSerialNumber.setCurrentCycle(currentCycle);
                        // 更新当前序列号，当前周期
                        updateSerialNumber.set("currentSequence", 0L);
                        updateSerialNumber.set("currentCycle", currentCycle);
                    }
                }
                int step = documentSerialNumber.getStep();
                long end = documentSerialNumber.getCurrentSequence() + step;
                // 更新当前序列号
                updateSerialNumber.set("currentSequence", end);
                // 更新
                mongoTemplate.upsert(query, updateSerialNumber, DocumentSerialNumber.class, "serialNumber");
            }
        } catch (InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        } finally {
            lock.unlock();
        }
        if (null == documentSerialNumber) {
            return null;
        }
        int step = documentSerialNumber.getStep();
        // 处理流水号
        // 缓存MessageFormat
        MessageFormat messageFormat = MSG_FORMAT_MAP.get(code);
        if (null == messageFormat) {
            messageFormat = new MessageFormat(documentSerialNumber.getTemplate());
            MSG_FORMAT_MAP.put(code, messageFormat);
        }
        long start = documentSerialNumber.getCurrentSequence();
        start += step;
        return formatNumber(messageFormat, documentSerialNumber, start);
    }

    @Override
    public DocumentSerialNumber getById(Long id) {
        return mongoTemplate.findById(id, DocumentSerialNumber.class, "serialNumber");
    }

    @Override
    public void removeById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, DocumentSerialNumber.class, "serialNumber");
    }

    @Override
    public long remove() {
        DeleteResult deleteResult = mongoTemplate.remove(DocumentSerialNumber.class, "serialNumber");
        return deleteResult.getDeletedCount();
    }

    @Override
    public List<DocumentSerialNumber> findAll() {
        return mongoTemplate.findAll(DocumentSerialNumber.class, "serialNumber");
    }

    /**
     * 格式化流水号
     *
     * @param messageFormat        messageFormat
     * @param documentSerialNumber documentSerialNumber
     * @param start                start
     * @return string
     */
    private String formatNumber(MessageFormat messageFormat, DocumentSerialNumber documentSerialNumber, long start) {
        // 周期 + 增长值
        Object[] formatArgs;
        // 填充字符串
        String fullChar = "0";
        String fullBegin = fullBegin(String.valueOf(start), documentSerialNumber.getLength(), fullChar);
        if (documentSerialNumber.getType() == SerialNumberTypeEnum.CYCLE.ordinal()) {
            formatArgs = new String[2];
            formatArgs[0] = documentSerialNumber.getCurrentCycle();
            formatArgs[1] = fullBegin;
        } else {
            formatArgs = new String[1];
            formatArgs[0] = fullBegin;
        }
        // 处理前缀
        String prefix = documentSerialNumber.getPrefix();
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
