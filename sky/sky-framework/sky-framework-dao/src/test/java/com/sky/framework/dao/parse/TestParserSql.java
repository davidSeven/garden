package com.sky.framework.dao.parse;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.TableStat;
import org.junit.Test;

import java.util.List;

/**
 * @date 2020-12-10 010 17:26
 */
public class TestParserSql {

    @Test
    public void testParser() {
        String sql = "select id,name from user where id = 1 and state = '1'" +
                " and (select id from type where state = 1)";

        // 新建 MySQL Parser
        SQLStatementParser parser = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement statement = parser.parseStatement();

        // 使用visitor来访问AST
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);

        // 从visitor中拿出你所关注的信息
        System.out.println(visitor.getColumns());
        List<TableStat.Condition> conditions = visitor.getConditions();
        System.out.println(conditions);
    }
}
