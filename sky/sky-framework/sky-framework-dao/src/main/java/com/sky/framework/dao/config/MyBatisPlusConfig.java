package com.sky.framework.dao.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.sky.framework.dao.plugins.SqlContextInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2020-10-28 028 15:18
 */
@Configuration
@MapperScan("com.sky.**.dao")
public class MyBatisPlusConfig {

    // @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public SqlContextInterceptor sqlContextInterceptor() {
        return new SqlContextInterceptor();
    }
}
