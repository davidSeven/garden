package com.sky.acc.service.impl;

import com.sky.acc.api.dto.AccountDto;
import com.sky.acc.api.model.Account;
import com.sky.acc.service.AccountLockService;
import com.sky.acc.service.AccountService;
import com.sky.framework.api.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AccountLockServiceImpl implements AccountLockService {
    private final Logger logger = LoggerFactory.getLogger(AccountLockServiceImpl.class);

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private AccountService accountService;

    @Override
    public boolean create(Account account) {
        String customerCode = account.getCustomerCode();
        if (StringUtils.isEmpty(customerCode)) {
            return false;
        }
        String key = "Account:" + customerCode;
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock(15, TimeUnit.SECONDS)) {
                this.accountService.create(customerCode);
                return true;
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            throw new CommonException(500, "创建账户失败，" + e.getMessage());
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean in(AccountDto dto) {
        String key = "Account:" + dto.getCustomerCode() + ":" + dto.getCurrency();
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock(15, TimeUnit.SECONDS)) {
                this.accountService.in(dto);
                return true;
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            throw new CommonException(500, "入账失败，" + e.getMessage());
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean out(AccountDto dto) {
        if (null == dto.getFreeze()) {
            dto.setFreeze(false);
        }
        String key = "Account:" + dto.getCustomerCode() + ":" + dto.getCurrency();
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock(15, TimeUnit.SECONDS)) {
                this.accountService.out(dto);
                return true;
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            throw new CommonException(500, "出账失败，" + e.getMessage());
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean freeze(AccountDto dto) {
        return false;
    }

    @Override
    public boolean unFreeze(AccountDto dto) {
        return false;
    }
}
