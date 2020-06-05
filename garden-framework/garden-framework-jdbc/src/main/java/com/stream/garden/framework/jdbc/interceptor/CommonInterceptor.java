package com.stream.garden.framework.jdbc.interceptor;

import com.google.common.collect.Lists;
import com.stream.garden.framework.api.model.Context;
import com.stream.garden.framework.jdbc.util.PluginUtil;
import com.stream.garden.framework.util.ContextUtil;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author garden
 * @date 2020-06-05 13:59
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class CommonInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    private final String UPDATION_DATE = "UPDATION_DATE";
    private final String UPDATED_BY = "UPDATED_BY";
    private final String CREATED_BY = "CREATED_BY";
    private final String CREATION_DATE = "CREATION_DATE";
    private final String VERSION = "VERSION";
    private final String PREPARE = "prepare";
    private final List<String> IGNORE_MAPPER_LIST = Lists.newArrayList("xxx");
    private Properties properties = null;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String interceptMethod = invocation.getMethod().getName();
        if (!PREPARE.equals(interceptMethod)) {
            return invocation.proceed();
        }
        StatementHandler handler = (StatementHandler) PluginUtil.processTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 忽略mapper
        long count = IGNORE_MAPPER_LIST.stream().filter(data -> ms.getId().startsWith(data)).count();
        if (count > 0) {
            return invocation.proceed();
        }
        ;
        SqlCommandType sqlCmdType = ms.getSqlCommandType();
        if (sqlCmdType != SqlCommandType.UPDATE && sqlCmdType != SqlCommandType.INSERT) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();
        if (logger.isDebugEnabled()) {
            logger.debug("originalSql before: {}", originalSql);
        }
        Context context = ContextUtil.getContext();
        if (sqlCmdType == SqlCommandType.UPDATE) {
            originalSql = addUpdateCommonToSql(originalSql, context);
        } else {
            originalSql = addInsertCommonToSql(originalSql, context);
        }
        // 删除预占
        deleteParameterMapping(boundSql, "CREATIONDATE");
        deleteParameterMapping(boundSql, "CREATEDBY");
        deleteParameterMapping(boundSql, "UPDATIONDATE");
        deleteParameterMapping(boundSql, "UPDATEDBY");
        metaObject.setValue("delegate.boundSql.sql", originalSql);
        if (logger.isDebugEnabled()) {
            logger.debug("originalSql after: " + originalSql);
        }
        return invocation.proceed();
    }

    private String addInsertCommonToSql(String originalSql, Context context) {
        try {
            Statement stmt = CCJSqlParserUtil.parse(originalSql);
            if (!(stmt instanceof Insert)) {
                return originalSql;
            }
            Insert insert = (Insert) stmt;
            buildInsertCommonColumnExpression(insert, CREATION_DATE, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), Date.class);
            buildInsertCommonColumnExpression(insert, CREATED_BY, context.getUserId(), String.class);
            buildInsertCommonColumnExpression(insert, UPDATION_DATE, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), Date.class);
            buildInsertCommonColumnExpression(insert, UPDATED_BY, context.getUserId(), String.class);
            // buildInsertCommonColumnExpression(insert, VERSION, "1", BigDecimal.class);
            return stmt.toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return originalSql;
        }
    }

    private void buildInsertCommonColumnExpression(Insert insert, String columnName, String columnValue, Class<?> cla) {
        // 删除columnName
        deleteInsertColumnAndExpressions(insert, columnName);
        List<Column> columns = insert.getColumns();
        // 列 version
        Column versionColumn = new Column();
        versionColumn.setColumnName(columnName);
        columns.add(versionColumn);

        ExpressionList expressionList = (ExpressionList) insert.getItemsList();
        List<Expression> expressions = expressionList.getExpressions();
        if (cla == Date.class) {
            expressions.add(new StringValue(columnValue));
        }
        if (cla == String.class) {
            expressions.add(new StringValue(columnValue));
        }
        if (cla == BigDecimal.class) {
            expressions.add(new LongValue(Long.parseLong(columnValue)));
        }
    }

    private String addUpdateCommonToSql(String originalSql, Context context) {
        try {
            Statement stmt = CCJSqlParserUtil.parse(originalSql);
            if (!(stmt instanceof Update)) {
                return originalSql;
            }
            Update update = (Update) stmt;
            // 删除columnName
            deleteUpdateColumnAndExpressions(update, CREATED_BY);
            deleteUpdateColumnAndExpressions(update, CREATION_DATE);
            buildUpdateCommonColumnExpression(update, UPDATION_DATE, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), Date.class);
            buildUpdateCommonColumnExpression(update, UPDATED_BY, context.getUserId(), String.class);
            // buildVersionExpression(update);
            return stmt.toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return originalSql;
        }
    }

    private void buildUpdateCommonColumnExpression(Update update, String columnName, String columnValue, Class<?> cla) {
        // 删除columnName
        deleteUpdateColumnAndExpressions(update, columnName);
        List<Column> columns = update.getColumns();
        // 列 version
        Column versionColumn = new Column();
        versionColumn.setColumnName(columnName);
        columns.add(versionColumn);

        List<Expression> expressions = update.getExpressions();
        if (cla == Date.class) {
            expressions.add(new StringValue(columnValue));
        }
        if (cla == String.class) {
            expressions.add(new StringValue(columnValue));
        }
    }

    private void buildVersionExpression(Update update) {
        String versionColumnName = "VERSION";
        // 删除version
        deleteUpdateColumnAndExpressions(update, versionColumnName);
        List<Column> columns = update.getColumns();
        // 列 version
        Column versionColumn = new Column();
        versionColumn.setColumnName(versionColumnName);
        columns.add(versionColumn);
        // 值 version+1
        List<Expression> expressions = update.getExpressions();

        CaseExpression caseExpression = new CaseExpression();
        WhenClause whenClause = new WhenClause();
        IsNullExpression isNullExpression = new IsNullExpression();
        isNullExpression.setLeftExpression(versionColumn);
        whenClause.setWhenExpression(isNullExpression);
        whenClause.setThenExpression(new LongValue(0));
        List<WhenClause> whenClauses = Lists.newArrayList();
        whenClauses.add(whenClause);
        caseExpression.setWhenClauses(whenClauses);
        caseExpression.setElseExpression(versionColumn);

        Addition add = new Addition();
        add.setLeftExpression(caseExpression);
        add.setRightExpression(new LongValue(1));
        expressions.add(add);
    }

    private void deleteInsertColumnAndExpressions(Insert insert, String columnName) {
        List<Column> columns = insert.getColumns();
        int index = -1;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getColumnName().equalsIgnoreCase(columnName)) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            columns.remove(index);
            ExpressionList expressionList = (ExpressionList) insert.getItemsList();
            expressionList.getExpressions().remove(index);
        }
    }

    private void deleteUpdateColumnAndExpressions(Update update, String columnName) {
        List<Column> columns = update.getColumns();
        int index = -1;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getColumnName().equalsIgnoreCase(columnName)) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            columns.remove(index);
            update.getExpressions().remove(index);
        }
    }

    private void deleteParameterMapping(BoundSql boundSql, String columnName) {
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
        int p_index = -1;
        for (int i = 0; i < parameterMappingList.size(); i++) {
            String property = parameterMappingList.get(i).getProperty();
            property = property.contains(".") ? property.substring(property.indexOf(".") + 1, property.length()) : property;
            if (property.equalsIgnoreCase(columnName)) {
                p_index = i;
                break;
            }
        }
        if (p_index > -1) {
            parameterMappingList.remove(p_index);
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ParameterHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        if (null != properties && !properties.isEmpty()) this.properties = properties;
    }
}
