package com.sky.framework.dao.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sky.framework.api.context.RequestContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @date 2020-10-28 028 16:29
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        RequestContext currentContext = RequestContext.getCurrentContext();
        String userCode = currentContext.getUserCode();
        String requestId = currentContext.getRequestId();
        this.setFieldValByName("createBy", userCode, metaObject);
        this.setFieldValByName("createDate", now, metaObject);
        this.setFieldValByName("updateBy", userCode, metaObject);
        this.setFieldValByName("updateDate", now, metaObject);
        this.setFieldValByName("traceId", requestId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        RequestContext currentContext = RequestContext.getCurrentContext();
        String userCode = currentContext.getUserCode();
        String requestId = currentContext.getRequestId();
        this.setFieldValByName("updateBy", userCode, metaObject);
        this.setFieldValByName("updateDate", now, metaObject);
        this.setFieldValByName("traceId", requestId, metaObject);
    }
}
