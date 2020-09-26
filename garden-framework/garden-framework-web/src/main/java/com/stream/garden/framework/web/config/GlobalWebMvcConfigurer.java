package com.stream.garden.framework.web.config;

import com.stream.garden.framework.web.convert.ConverterBoolean;
import com.stream.garden.framework.web.convert.ConverterDate;
import com.stream.garden.framework.web.convert.ConverterTimestamp;
import com.stream.garden.framework.web.filter.ContextFilter;
import com.stream.garden.framework.web.filter.PermissionFilter;
import com.stream.garden.framework.web.filter.RequestFilter;
import com.stream.garden.framework.web.interceptor.ContextInterceptor;
import com.stream.garden.framework.web.permission.IPermissionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/**
 * @author garden
 * @date 2019-06-19 17:33
 */
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer, InitializingBean {
    private final GlobalConfig globalConfig;
    private ApplicationContext applicationContext;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GlobalWebMvcConfigurer(GlobalConfig globalConfig, ApplicationContext applicationContext) {
        this.globalConfig = globalConfig;
        this.applicationContext = applicationContext;
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
    public FilterRegistrationBean<ContextFilter> getContextFilter() {
        final FilterRegistrationBean<ContextFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new ContextFilter(globalConfig));
        filter.setName("contextFilter");
        filter.addUrlPatterns("/*");
        filter.setOrder(2);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<PermissionFilter> getPermissionFilter() {
        final FilterRegistrationBean<PermissionFilter> filter = new FilterRegistrationBean<>();
        IPermissionData permissionData = null;
        if (null != applicationContext) {
            Map<String, IPermissionData> permissionDataMap = applicationContext.getBeansOfType(IPermissionData.class);
            for (Map.Entry<String, IPermissionData> entry : permissionDataMap.entrySet()) {
                permissionData = entry.getValue();
                if (null != permissionData) {
                    break;
                }
            }
        }
        filter.setFilter(new PermissionFilter(globalConfig, permissionData));
        filter.setName("permissionFilter");
        filter.addUrlPatterns("/*");
        filter.setOrder(10);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<RequestFilter> getRequestFilter() {
        final FilterRegistrationBean<RequestFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new RequestFilter(globalConfig));
        filter.setName(RequestFilter.FILTER_NAME);
        filter.addUrlPatterns(RequestFilter.URL_PATTERNS);
        filter.setOrder(-20000);
        return filter;
    }

    /**
     * https://blog.csdn.net/kanaiji123/article/details/88695332
     *
     * @return OrderedHiddenHttpMethodFilter
     */
    /*@Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain) throws
                    ServletException, IOException {
                filterChain.doFilter(request, response);
            }
        };
    }*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContextInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/test");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConverterDate());
        registry.addConverter(new ConverterTimestamp());
        registry.addConverter(new ConverterBoolean());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("---------------------------------------------");
        logger.debug("---------------------------------------------");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/static/vendor/");
        // registry.addResourceHandler("/images2/**").addResourceLocations("file:D:/images/");
        String os = System.getProperty("os.name");
        String uploadPath = globalConfig.getUploadPath();
        if (os.toLowerCase().startsWith("win")) {
            // 判断有没有盘符
            if (!uploadPath.contains(":")) {
                // 添加路径
                if (!uploadPath.startsWith("/")) {
                    uploadPath = "/" + uploadPath;
                }
                if (!uploadPath.endsWith("/")) {
                    uploadPath = uploadPath + "/";
                }
                // 默认C盘
                uploadPath = "C:" + uploadPath;
            }
        }
        registry.addResourceHandler("/static/images/**").addResourceLocations("file:" + uploadPath);
        // swagger配置
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
