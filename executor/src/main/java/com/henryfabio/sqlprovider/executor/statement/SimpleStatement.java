package com.henryfabio.sqlprovider.executor.statement;

import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Henry Fábio
 */
@RequiredArgsConstructor
public final class SimpleStatement implements AutoCloseable {

    private final PreparedStatement preparedStatement;

    public static SimpleStatement of(PreparedStatement preparedStatement) {
        return new SimpleStatement(preparedStatement);
    }

    public void set(int parameterIndex, Object value) {
        try {
            preparedStatement.setObject(parameterIndex, value);
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public void executeUpdate() {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public SimpleResultSet executeQuery() {
        try {
            return new SimpleResultSet(preparedStatement.executeQuery());
        } catch (SQLException t) {
            t.printStackTrace();
            throw new NullPointerException("ResultSet can't be null");
        }
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
    }

}
