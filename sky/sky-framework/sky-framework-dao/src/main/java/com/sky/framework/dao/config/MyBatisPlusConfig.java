package com.sky.framework.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2020-10-28 028 15:18
 */
@Configuration
@MapperScan("com.sky.**.dao")
public class MyBatisPlusConfig {
}
