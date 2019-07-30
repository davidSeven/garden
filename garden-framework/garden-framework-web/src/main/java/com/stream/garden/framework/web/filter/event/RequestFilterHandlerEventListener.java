package com.stream.garden.framework.web.filter.event;

import com.stream.garden.framework.web.filter.RequestFilterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author garden
 * @date 2019-07-30 13:46
 */
@Component
public class RequestFilterHandlerEventListener implements ApplicationListener<RequestFilterHandlerEvent>, ApplicationContextAware, InitializingBean {
    public static boolean hasRequestFilterHandler = false;
    private Logger logger = LoggerFactory.getLogger(RequestFilterHandlerEventListener.class);
    private Collection<RequestFilterHandler> requestFilterHandlers;
    private ApplicationContext applicationContext;

    @Async
    @Override
    public void onApplicationEvent(RequestFilterHandlerEvent event) {
        EventObject eventObject = event.getEventObject();
        Map<String, Object> objectMap = eventObject.getObjectMap();
        String uri = (String) objectMap.get(RequestFilterHandlerEvent.URI);
        long times = (long) objectMap.get(RequestFilterHandlerEvent.TIMES);
        logger.info("uri:{}", uri);
        logger.info("times:{}", times);

        for (RequestFilterHandler handler : requestFilterHandlers) {
            handler.after(uri, times);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 获取RequestFilterHandler所有的实现
        Map<String, RequestFilterHandler> requestFilterHandlerMap = this.applicationContext.getBeansOfType(RequestFilterHandler.class);
        if (!requestFilterHandlerMap.isEmpty()) {
            requestFilterHandlers = requestFilterHandlerMap.values();
            hasRequestFilterHandler = true;
        }
    }

    public Collection<RequestFilterHandler> getRequestFilterHandlers() {
        return requestFilterHandlers;
    }

    public void setRequestFilterHandlers(Collection<RequestFilterHandler> requestFilterHandlers) {
        this.requestFilterHandlers = requestFilterHandlers;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
