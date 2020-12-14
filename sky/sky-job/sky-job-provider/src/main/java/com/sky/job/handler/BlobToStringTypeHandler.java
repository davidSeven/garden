package com.sky.job.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

/**
 * @date 2020-11-23 023 14:48
 */
public class BlobToStringTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Blob blob = resultSet.getBlob(columnName);
        if (null == blob) {
            return null;
        }
        return new String(blob.getBytes(1, (int) blob.length()));
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Blob blob = resultSet.getBlob(columnIndex);
        if (null == blob) {
            return null;
        }
        return new String(blob.getBytes(1, (int) blob.length()));
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Blob blob = callableStatement.getBlob(columnIndex);
        if (null == blob) {
            return null;
        }
        return new String(blob.getBytes(1, (int) blob.length()));
    }
}
