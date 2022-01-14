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
        this.compareAndSetter("createBy", userCode, metaObject);
        this.compareAndSetter("createDate", now, metaObject);
        this.compareAndSetter("updateBy", userCode, metaObject);
        this.compareAndSetter("updateDate", now, metaObject);
        this.compareAndSetter("traceId", requestId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        RequestContext currentContext = RequestContext.getCurrentContext();
        String userCode = currentContext.getUserCode();
        String requestId = currentContext.getRequestId();
        this.compareAndSetter("updateBy", userCode, metaObject);
        this.compareAndSetter("updateDate", now, metaObject);
        this.compareAndSetter("traceId", requestId, metaObject);
    }

    private void compareAndSetter(String fieldName, Object fieldVal, MetaObject metaObject) {
        // 如果值是空的就赋值
        if (metaObject.hasGetter(fieldName) && this.getFieldValByName(fieldName, metaObject) == null) {
            this.setFieldValByName(fieldName, fieldVal, metaObject);
        }
    }
}
