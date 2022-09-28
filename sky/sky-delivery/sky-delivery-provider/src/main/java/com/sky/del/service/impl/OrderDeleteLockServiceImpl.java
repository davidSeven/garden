package com.sky.del.service.impl;

import com.sky.del.api.model.OrderDelete;
import com.sky.del.config.DeliveryConfiguration;
import com.sky.del.service.OrderDeleteLockService;
import com.sky.del.service.OrderDeleteService;
import com.sky.del.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OrderDeleteLockServiceImpl implements OrderDeleteLockService {
    private final Logger logger = LoggerFactory.getLogger(OrderDeleteLockServiceImpl.class);

    @Autowired
    private OrderDeleteService orderDeleteService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedissonClient redissonClient;

    @Async(value = DeliveryConfiguration.THREAD_POOL_EXECUTOR_ORDER_DELETE)
    @Override
    public void execute(OrderDelete orderDelete) {
        String key = "OrderDelete:execute:" + orderDelete.getId();
        RLock lock = this.redissonClient.getLock(key);
        long st = System.currentTimeMillis();
        try {
            if (lock.tryLock(0, TimeUnit.SECONDS)) {
                // 调用业务
                this.orderService.delete(orderDelete.getOrderNo());
                // 任务成功
                this.orderDeleteService.success(orderDelete.getId());
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            Thread currentThread = Thread.currentThread();
            if (!currentThread.isInterrupted()) {
                currentThread.interrupt();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 任务失败
            this.orderDeleteService.fail(orderDelete.getId(), e.getMessage(), orderDelete.getHandleSize(), (System.currentTimeMillis() - st));
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
