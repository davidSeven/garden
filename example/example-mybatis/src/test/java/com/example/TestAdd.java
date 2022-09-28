package com.example;

import com.example.dao.TestDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestAdd {

    private AtomicLong atomicLong = new AtomicLong(0L);

    @Test
    public void run() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 50000; i++) {
            System.out.println("add");
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    add(sqlSessionFactory);
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCode() {
        return "CODE" + String.format("%010d", atomicLong.incrementAndGet());
    }

    public void add(SqlSessionFactory sqlSessionFactory) {
        try {
            // 管理一个SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 查询两次，第一次缓存到SqlSession中了，第二次去查询的时候从缓存中获取
            TestDao testDao = sqlSession.getMapper(TestDao.class);
            com.example.model.Test entity = new com.example.model.Test();
            entity.setCode(getCode());
            entity.setName("张三");
            System.out.println(testDao.add(entity));
            TimeUnit.MILLISECONDS.sleep(new Random(100).nextInt());
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
