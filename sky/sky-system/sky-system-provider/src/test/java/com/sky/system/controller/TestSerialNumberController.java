package com.sky.system.controller;

import ch.qos.logback.classic.LoggerContext;
import cn.hutool.bloomfilter.BitSetBloomFilter;
import cn.hutool.bloomfilter.BloomFilter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sky.framework.api.dto.ResponseDto;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class TestSerialNumberController {

    final String url = "http://localhost:18080";

    @Before
    public void before() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("org.apache.http").setLevel(ch.qos.logback.classic.Level.ERROR);
    }

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(64);
        AtomicLong sal = new AtomicLong();
        AtomicLong fal = new AtomicLong();
        long st = System.currentTimeMillis();
        TimeInterval timeInterval = DateUtil.timer();
        int threadCount = 64;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // 线程池最大64个
        // 200个线程，每个线程执行1000次
        BloomFilter bloomFilter = new BitSetBloomFilter(100000, 5000, 16);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 500; j++) {
                        try {
                            String asString = Request.Post(url + "/serial-number/generateNumber").bodyString("{\"code\":\"TEST_CODE\"}", ContentType.APPLICATION_JSON).execute().returnContent().asString();
                            ResponseDto<String> stringResponseDto = JSON.parseObject(asString, new TypeReference<ResponseDto<String>>() {
                            });
                            String data = stringResponseDto.getData();
                            if (!bloomFilter.add(data)) {
                                System.out.println("重复," + data);
                            }
                            long l = sal.incrementAndGet();
                            if (l % 10_000 == 0) {
                                System.out.println("sal:" + l + ", timer:" + timeInterval.intervalRestart());
                            }
                        } catch (IOException e) {
                            long l = fal.incrementAndGet();
                            if (l % 10_000 == 0) {
                                System.out.println("fal:" + l);
                            }
                        }
                    }
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("成功：" + sal.get());
        System.out.println("失败：" + fal.get());
        System.out.println("耗时：" + (System.currentTimeMillis() - st));
    }

    @Test
    public void test2() {
        String asString = null;
        try {
            asString = Request.Post(url + "/serial-number/generateNumber").bodyString("{\"code\":\"TEST_CODE\"}", ContentType.APPLICATION_JSON).execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
