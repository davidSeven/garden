package com.stream.garden.framework.web.filter.event;

import com.stream.garden.framework.web.filter.RequestFilterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author garden
 * @date 2019-07-30 14:08
 */
@Component
public class RequestFilterHandlerImpl implements RequestFilterHandler {
    private Logger logger = LoggerFactory.getLogger(RequestFilterHandlerImpl.class);

    @Override
    public void after(String uri, long times) {
        logger.info("uri:{}", uri);
        logger.info("times:{}", times);
    }
}
