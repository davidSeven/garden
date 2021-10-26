package com.example;

import com.example.dao.UserDao;
import com.example.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMyBatis {

    @Test
    public void run() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 查询两次数据库，SqlSession关闭了，缓存也被清掉了。一级缓存基于SqlSession
        getByName(sqlSessionFactory, "佟丽娅");
        getByName(sqlSessionFactory, "佟丽娅");

        // 管理一个SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 查询两次，第一次缓存到SqlSession中了，第二次去查询的时候从缓存中获取
        getByName(sqlSession, "佟丽娅");
        getByName(sqlSession, "佟丽娅");
        sqlSession.close();
    }

    /**
     * 根据mapper文件的路径来请求
     *
     * @param sqlSessionFactory sqlSessionFactory
     * @param name              name
     */
    public void getByName(SqlSessionFactory sqlSessionFactory, String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "com.example.dao.UserDao.getByName";
        User user = sqlSession.selectOne(statement, name);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 根据接口类来请求
     *
     * @param sqlSession sqlSession
     * @param name       name
     */
    public void getByName(SqlSession sqlSession, String name) {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getByName(name);
        System.out.println(user);
    }
}
