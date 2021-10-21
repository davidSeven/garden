package com.example;

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

        getByName(sqlSessionFactory, "佟丽娅");
        getByName(sqlSessionFactory, "佟丽娅");
    }

    public void getByName(SqlSessionFactory sqlSessionFactory, String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        String statement = "com.example.UserDao.getByName";

        User user = sqlSession.selectOne(statement, name);
        System.out.println(user);

        sqlSession.close();
    }
}
