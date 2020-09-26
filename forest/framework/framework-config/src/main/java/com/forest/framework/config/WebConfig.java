package com.forest.framework.config;

import com.forest.framework.common.constant.CommonConstant;
import com.forest.framework.interceptor.ContextInterceptor;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.List;

/**
 * WebConfig web组件配置
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Value("${characterEncoding:UTF-8}")
    private String characterEncoding;
    @Value("${spring.jackson.date-format:}")
    private String dateFormat;
    @Value("${info.config.tmp:}")
    private String tmp;
    @Value("${spring.profiles.active:local}")
    private String profile;

    @Autowired
    private ContextInterceptor contextInterceptor;

    @Bean
    public RequestContextListener requestContextListener() {
        log.debug("WebConfig requestContextListener");
        return new RequestContextListener();
    }

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean() {
        log.debug("WebConfig HystrixMetricsStreamServlet");
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        if (!"local".equalsIgnoreCase(profile)) {
            String location = tmp;
            if (StringUtils.isEmpty(location)) {
                location = System.getProperty("user.home") + "/data/tmp";
            }
            File tmpFile = new File(location);
            if (!tmpFile.exists()) {
                boolean bool = tmpFile.mkdirs();
                log.trace(String.valueOf(bool));
            }
            factory.setLocation(location);
        }
        return factory.createMultipartConfig();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        log.debug("WebConfig configurePathMatch");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        log.debug("WebConfig configureContentNegotiation");
        configurer.defaultContentType(CommonConstant.MEDIA_TYPE);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        log.debug("WebConfig configureAsyncSupport");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("WebConfig configureDefaultServletHandling");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConverterDate());
        registry.addConverter(new ConverterTimestamp());
        registry.addConverter(new ConverterBoolean());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug("WebConfig addInterceptors");
        registry.addInterceptor(contextInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.debug("WebConfig addResourceHandlers");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.debug("WebConfig addCorsMappings");
        registry.addMapping("/**")
                .allowedOrigins(CorsConfiguration.ALL)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .exposedHeaders()
                .maxAge(3600L);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("WebConfig addViewControllers");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        log.debug("WebConfig configureViewResolvers");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        log.debug("WebConfig addArgumentResolvers");
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        log.debug("WebConfig addReturnValueHandlers");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.debug("WebConfig configureMessageConverters");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.debug("WebConfig extendMessageConverters");
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        log.debug("WebConfig configureHandlerExceptionResolvers");
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        log.debug("WebConfig extendHandlerExceptionResolvers");
    }

    @Override
    public Validator getValidator() {
        log.debug("WebConfig getValidator");
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        log.debug("WebConfig getMessageCodesResolver");
        return null;
    }

}
