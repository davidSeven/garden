package com.sky.framework.dao.parse;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyuyuan
 * @date 2020-12-10 010 17:44
 */
public class TestSqlUtils {

    @Test
    public void testSqlUtils() {
        String sql = "select id,name from user where id = 1 and state = '1'" +
                " and (select id from type where state = 1)";
        DbType dbType = JdbcConstants.MYSQL;

        //格式化输出
        String result = SQLUtils.format(sql, dbType);
        System.out.println(result); // 缺省大写格式
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

        //解析出的独立语句的个数
        System.out.println("size is:" + stmtList.size());
        for (int i = 0; i < stmtList.size(); i++) {

            SQLStatement stmt = stmtList.get(i);

            PGSchemaStatVisitor visitor = new PGSchemaStatVisitor();
            stmt.accept(visitor);

            Set<TableStat.Column> groupby_col = visitor.getGroupByColumns();
            //
            for (Iterator iterator = groupby_col.iterator(); iterator.hasNext(); ) {
                TableStat.Column column = (TableStat.Column) iterator.next();
                System.out.println("[GROUP]" + column.toString());
            }
            //获取表名称
            System.out.println("table names:");
            Map<TableStat.Name, TableStat> tabmap = visitor.getTables();
            for (Iterator iterator = tabmap.keySet().iterator(); iterator.hasNext(); ) {
                TableStat.Name name = (TableStat.Name) iterator.next();
                System.out.println(name.toString() + " - " + tabmap.get(name).toString());
            }
            //获取操作方法名称,依赖于表名称
            System.out.println("Manipulation : " + visitor.getTables());
            //获取字段名称
            System.out.println("fields : " + visitor.getColumns());
        }
    }
}
