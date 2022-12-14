package com.sky.framework.dao.plugins;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

@Intercepts(
        {
                @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class NoSqlContextInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(SqlContextInterceptor.class);

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Executor executor = (Executor) invocation.getTarget();

        Method invocationMethod = invocation.getMethod();

        Object[] invocationArgs = invocation.getArgs();

        MappedStatement mappedStatement = (MappedStatement) invocationArgs[0];

        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        if (!SqlCommandType.SELECT.equals(sqlCommandType)) {
            return invocation.proceed();
        }

        Object parameter = invocationArgs[1];
        RowBounds rowBounds = (RowBounds) invocationArgs[2];
        ResultHandler<?> resultHandler = (ResultHandler<?>) invocationArgs[3];

        CacheKey cacheKey;
        BoundSql boundSql;

        //???????????????????????????????????????
        if (invocationArgs.length == 4) {
            //4 ????????????
            boundSql = mappedStatement.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
        } else {
            //6 ????????????
            cacheKey = (CacheKey) invocationArgs[4];
            boundSql = (BoundSql) invocationArgs[5];
        }

        // ?????????????????????????????????

        return executor.query(mappedStatement, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        this.logger.info(this.properties.toString());
    }
}
