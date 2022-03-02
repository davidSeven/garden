package com.sky.framework.interceptor;

import com.sky.framework.api.context.RequestContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableFeignClients(basePackages = "com.sky")
public class FeignRequestInterceptor implements RequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Value("${spring.application.name}")
    String springApplicationName;

    @Override
    public void apply(RequestTemplate template) {
        // logger.info(template.bodyTemplate());
        // logger.info(template.method());
        // logger.info(template.url());
        // 这里不能强制设置Accept和Content-Type，不然通过feign的方式调附件上传接口会丢失file
        // template.header("Accept", MediaType.ALL_VALUE);
        // template.header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 获取header中的属性
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if ("content-type".equalsIgnoreCase(name)) {
                        break;
                    }
                    if ("content-length".equalsIgnoreCase(name)) {
                        break;
                    }
                    // template.header(name, request.getHeader(name));
                    Enumeration<String> enumeration = request.getHeaders(name);
                    List<String> iterable = new LinkedList<>();
                    while (enumeration.hasMoreElements()) {
                        iterable.add(enumeration.nextElement());
                    }
                    template.header(name, iterable);
                }
            }
        }
        // 仅设置当前线程中可能发生改变的属性，Http Header中既存的属性无需再进行设置
        RequestContext context = RequestContext.getCurrentContext();
        setHeader(template, RequestContext.ORIGIN_APP, springApplicationName);
        setHeader(template, RequestContext.REQUEST_ID, context.getRequestId());
    }

    /**
     * setHeader
     *
     * @param template RequestTemplate
     * @param name     String
     * @param value    Object
     */
    private void setHeader(RequestTemplate template, String name, Object value) {
        if (!StringUtils.isEmpty(value)) {
            template.header(name, value.toString());
        }
    }

}
