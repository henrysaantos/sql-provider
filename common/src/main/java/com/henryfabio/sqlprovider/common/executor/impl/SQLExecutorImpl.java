package com.henryfabio.sqlprovider.common.executor.impl;

import com.henryfabio.sqlprovider.common.SQLProvider;
import com.henryfabio.sqlprovider.common.executor.SQLExecutor;
import com.henryfabio.sqlprovider.common.result.SimpleResultSet;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@RequiredArgsConstructor
public final class SQLExecutorImpl implements SQLExecutor {

    private final SQLProvider provider;

    public void updateQuery(String sql, Consumer<PreparedStatement> consumer) {
        provider.consumeConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                consumer.accept(statement);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public <T> T resultQuery(String sql, Consumer<PreparedStatement> consumer, Function<SimpleResultSet, T> function) {
        AtomicReference<T> value = new AtomicReference<>();
        provider.consumeConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                consumer.accept(statement);

                try (ResultSet resultSet = statement.executeQuery()) {
                    SimpleResultSet simpleResultSet = new SimpleResultSet(resultSet);
                    value.set(function.apply(simpleResultSet));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return value.get();
    }


}
