package com.sky.framework.dao.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.sky.framework.dao.methods.PhysicalDelete;
import com.sky.framework.dao.methods.PhysicalDeleteBatchIds;
import com.sky.framework.dao.methods.PhysicalDeleteById;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @date 2020-11-03 003 11:21
 */
@Component
public class MyDefaultSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new PhysicalDelete());
        methodList.add(new PhysicalDeleteBatchIds());
        methodList.add(new PhysicalDeleteById());
        return methodList;
    }
}
