package com.stream.garden.warehouse.service;

import java.util.concurrent.TimeUnit;

/**
 * @author garden
 * @date 2020-04-01 15:22
 */
public interface DistributedLocker {

    void lock(String lockKey);

    void unlock(String lockKey);

    void lock(String lockKey, int timeout);

    void lock(String lockKey, TimeUnit unit, int timeout);
}
