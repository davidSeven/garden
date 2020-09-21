package com.stream.forest.framework.interceptor;

import com.stream.forest.framework.common.context.RequestContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.stream.forest")
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${spring.application.name}")
    String springApplicationName;

    @Override
    public void apply(RequestTemplate template) {
        log.trace(template.bodyTemplate());
        log.trace(template.method());
        log.trace(template.url());
        // 这里不能强制设置Accept和Content-Type，不然通过feign的方式调附件上传接口会丢失file
        // template.header("Accept", MediaType.ALL_VALUE);
        // template.header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 获取header中的属性
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);
                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    if ("content-type".equalsIgnoreCase(name) && value.contains("multipart/form-data")) {
                        break;
                    }
                    template.header(name, value);
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
