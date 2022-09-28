package com.sky.job.config;

import com.sky.job.handler.BlobToStringTypeHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @date 2020-11-23 023 14:49
 */
@Configuration
public class BaseConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Bean("JobClientHttpRequestFactory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 单位为ms
        // 10分钟的超时
        factory.setReadTimeout(10 * 60 * 1000);
        // 单位为ms
        // 10分钟的超时
        factory.setConnectTimeout(10 * 60 * 1000);
        return factory;
    }

    @Bean("jobRestTemplate")
    @LoadBalanced
    public RestTemplate jobRestTemplate(@Qualifier("JobClientHttpRequestFactory") ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @PostConstruct
    public void handlerSqlSessionFactoryList() {
        if (CollectionUtils.isNotEmpty(sqlSessionFactoryList)) {
            for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
                if (null != sqlSessionFactory) {
                    org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
                    configuration.getTypeHandlerRegistry().register(BlobToStringTypeHandler.class);
                }
            }
        }

    }
}
