package com.sky.framework.dao.plugins;

import com.sky.framework.api.context.RequestContext;
import com.sky.framework.api.context.SqlConditionContext;
import com.sky.framework.api.context.SqlContext;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 2020-11-20 020 9:15
 */
@Intercepts(
        {
                @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class SqlContextInterceptor implements Interceptor {
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
        ResultHandler resultHandler = (ResultHandler) invocationArgs[3];

        CacheKey cacheKey;
        BoundSql boundSql;

        //由于逻辑关系，只会进入一次
        if (invocationArgs.length == 4) {
            //4 个参数时
            boundSql = mappedStatement.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) invocationArgs[4];
            boundSql = (BoundSql) invocationArgs[5];
        }

        RequestContext requestContext = RequestContext.getCurrentContext();
        SqlContext sqlContext = requestContext.getSqlContext();

        if (null != sqlContext) {
            List<SqlConditionContext> conditionContextList = sqlContext.getConditionContextList();
            if (CollectionUtils.isNotEmpty(conditionContextList)) {
                String originalSql = boundSql.getSql();
                String dataScopeSql = invokeBoundSql(originalSql);

                BoundSql dataScopeBoundSql = new BoundSql(mappedStatement.getConfiguration(), dataScopeSql, boundSql.getParameterMappings(), parameter);
                return executor.query(mappedStatement, parameter, rowBounds, resultHandler, cacheKey, dataScopeBoundSql);
            }
        }

        return executor.query(mappedStatement, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

    private String invokeBoundSql(String originalSql) throws Exception {
        String convertSql = "";
        String reg = "<datascope>([^<]+)</datascope>";
        Matcher m = Pattern.compile(reg).matcher(originalSql);
        if (m.find()) {
            String x = m.group(1);
            // x = u.id
            String andSql = "and " + x + " in (select user_relation_id from user_relation where user_id = " + x + ")";
            convertSql = originalSql.replaceAll(reg, andSql);
        } else {
            convertSql = originalSql.replaceAll(reg, "1 <> 1");
        }
        return convertSql;
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
