package com.sky.framework.dao.parse;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import org.junit.Test;

import java.util.List;

/**
 * @date 2020-12-11 011 8:50
 */
public class TestSqlValid {

    @Test
    public void testSqlValid() {
        String sqls = "select ID from BCP_Prize";
        MySqlStatementParser parser = new MySqlStatementParser(sqls);
        List<SQLStatement> stmtList = parser.parseStatementList();

        // 将AST通过visitor输出
        StringBuilder out = new StringBuilder();
        MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);

        for (SQLStatement stmt : stmtList) {
            stmt.accept(visitor);
            System.out.println(out + ";");
            out.setLength(0);
        }
    }
}
