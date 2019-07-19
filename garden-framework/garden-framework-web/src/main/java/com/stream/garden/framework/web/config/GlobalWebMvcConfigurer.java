package com.stream.garden.framework.web.config;

import com.stream.garden.framework.web.filter.ContextFilter;
import com.stream.garden.framework.web.interceptor.ContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author garden
 * @date 2019-06-19 17:33
 */
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GlobalConfig globalConfig;

    @Autowired
    public GlobalWebMvcConfigurer(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        GlobalConfig.JwtConfig jwt = null;
        if (null != globalConfig && null != (jwt = globalConfig.getJwt())) {
            logger.debug("配置了jwt，name：{}", jwt.getName());
        } else {
            logger.debug("没有配置jwt");
        }
    }

    @Bean
    public FilterRegistrationBean<ContextFilter> setJwtFilter() {
        final FilterRegistrationBean<ContextFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new ContextFilter(globalConfig));
        filter.setName("jwtFilter");
        filter.addUrlPatterns("/*");
        filter.setOrder(2);
        return filter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContextInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/test");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("---------------------------------------------");
        logger.debug("---------------------------------------------");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:D:/images/");
    }
}
