package com.sky.del.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.del.api.constant.OrderDeleteConstant;
import com.sky.del.api.model.OrderDelete;
import com.sky.del.dao.OrderDeleteDao;
import com.sky.del.service.OrderDeleteLockService;
import com.sky.del.service.OrderDeleteService;
import com.sky.del.service.OrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderDeleteServiceImpl extends ServiceImpl<OrderDeleteDao, OrderDelete> implements OrderDeleteService {
    private final Logger logger = LoggerFactory.getLogger(OrderDeleteServiceImpl.class);

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDeleteLockService orderDeleteLockService;

    @Override
    public void add(String orderNo) {
        OrderDelete insertOrderDelete = new OrderDelete();
        insertOrderDelete.setOrderNo(orderNo);
        insertOrderDelete.setState(OrderDeleteConstant.State.INIT.name());
        insertOrderDelete.setHandleSize(0);
        insertOrderDelete.setNextHandleTime(new Date());
        this.save(insertOrderDelete);
    }

    @Override
    public void add(List<String> orderNos) {
        List<OrderDelete> orderDeleteList = new ArrayList<>(orderNos.size());
        for (String orderNo : orderNos) {
            OrderDelete insertOrderDelete = new OrderDelete();
            insertOrderDelete.setOrderNo(orderNo);
            insertOrderDelete.setState(OrderDeleteConstant.State.INIT.name());
            insertOrderDelete.setHandleSize(0);
            insertOrderDelete.setNextHandleTime(new Date());
            orderDeleteList.add(insertOrderDelete);
        }
        this.saveBatch(orderDeleteList);
    }

    @Override
    public int execute(OrderDelete orderDelete) {
        String key = "OrderDelete:execute";
        RLock lock = this.redissonClient.getLock(key);
        try {
            if (lock.tryLock(0, TimeUnit.SECONDS)) {
                String orderNo = orderDelete.getOrderNo();
                if (StringUtils.isNotEmpty(orderNo)) {
                    this.orderService.delete(orderNo);
                } else {
                    // 查询初始化的任务执行
                    LambdaQueryWrapper<OrderDelete> queryWrapper = Wrappers.lambdaQuery();
                    queryWrapper.select(OrderDelete::getId, OrderDelete::getOrderNo);
                    queryWrapper.and(qw -> {
                        qw.eq(OrderDelete::getState, OrderDeleteConstant.State.INIT.name())
                                .or()
                                .eq(OrderDelete::getState, OrderDeleteConstant.State.FAIL.name());
                    });
                    // 小于10次
                    queryWrapper.lt(OrderDelete::getHandleSize, 10);
                    // 处理时间小于等于当前时间的
                    queryWrapper.le(OrderDelete::getNextHandleTime, new Date());
                    queryWrapper.last("limit 200");
                    List<OrderDelete> orderDeleteList = this.list(queryWrapper);
                    if (CollectionUtils.isNotEmpty(orderDeleteList)) {
                        int handleCount = 0;
                        for (OrderDelete delete : orderDeleteList) {
                            try {
                                this.orderDeleteLockService.execute(delete);
                                handleCount++;
                            } catch (Exception e) {
                                logger.error(e.getMessage(), e);
                                // 出现异常终止
                                break;
                            }
                        }
                        return handleCount;
                    }
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            Thread currentThread = Thread.currentThread();
            if (!currentThread.isInterrupted()) {
                currentThread.interrupt();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return 0;
    }

    @Override
    public void success(Long id) {
        OrderDelete updateOrderDelete = new OrderDelete();
        updateOrderDelete.setId(id);
        updateOrderDelete.setState(OrderDeleteConstant.State.SUCCESS.name());
        this.updateById(updateOrderDelete);
    }

    @Override
    public void fail(Long id, String failMessage, int handleSize, long requestConsumeTime) {
        OrderDelete updateOrderDelete = new OrderDelete();
        updateOrderDelete.setId(id);
        updateOrderDelete.setState(OrderDeleteConstant.State.FAIL.name());
        if (StringUtils.isNotEmpty(failMessage) && failMessage.length() > 500) {
            failMessage = failMessage.substring(0, 500);
        }
        updateOrderDelete.setLastFailMessage(failMessage);
        updateOrderDelete.setLastRequestConsumeTime(requestConsumeTime);
        updateOrderDelete.setHandleSize(handleSize + 1);
        updateOrderDelete.setNextHandleTime(DateUtils.addMinutes(new Date(), 3));
        this.updateById(updateOrderDelete);
    }
}
