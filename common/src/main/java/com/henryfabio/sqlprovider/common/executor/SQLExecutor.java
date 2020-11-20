package com.henryfabio.sqlprovider.common.executor;

import com.henryfabio.sqlprovider.common.adapter.SQLAdapter;
import com.henryfabio.sqlprovider.common.adapter.provider.SQLAdapterProvider;
import com.henryfabio.sqlprovider.common.result.SimpleResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface SQLExecutor {

    void updateQuery(String sql, Consumer<PreparedStatement> consumer);

    default void updateQuery(String sql) {
        this.updateQuery(sql, statement -> {});
    }

    default <T> void updateOneQuery(String sql, Class<? extends SQLAdapter<T>> clazz, T target) {
        this.updateQuery(sql, statement -> {
            try {
                SQLAdapterProvider adapterProvider = SQLAdapterProvider.getInstance();
                adapterProvider.get(clazz).adaptStatement(statement, target);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    default <T> void updateManyQuery(String sql, Class<? extends SQLAdapter<T>> clazz, Iterable<T> targets) {
        for (T target : targets) {
            this.updateOneQuery(sql, clazz, target);
        }
    }

    <T> T resultQuery(String sql, Consumer<PreparedStatement> consumer, Function<SimpleResultSet, T> function);

    default <T> T resultQuery(String sql, Function<SimpleResultSet, T> function) {
        return this.resultQuery(sql, statement -> {}, function);
    }

    default <T> T resultOneQuery(String sql, Consumer<PreparedStatement> consumer, Class<? extends SQLAdapter<T>> clazz) {
        return this.resultQuery(sql, consumer, resultSet -> {
            SQLAdapterProvider adapterProvider = SQLAdapterProvider.getInstance();
            return adapterProvider.get(clazz).adaptResult(resultSet);
        });
    }

    default <T> T resultOneQuery(String sql, Class<? extends SQLAdapter<T>> clazz) {
        return this.resultOneQuery(sql, statement -> {}, clazz);
    }

    default <T> List<T> resultManyQuery(String sql, Consumer<PreparedStatement> consumer, Class<? extends SQLAdapter<T>> clazz) {
        return this.resultQuery(sql, consumer, resultSet -> {
            SQLAdapterProvider adapterProvider = SQLAdapterProvider.getInstance();
            SQLAdapter<T> sqlAdapter = adapterProvider.get(clazz);

            List<T> resultList = new LinkedList<>();
            while (resultSet.next())
                resultList.add(sqlAdapter.adaptResult(resultSet));
            return resultList;
        });
    }

    default <T> List<T> resultManyQuery(String sql, Class<? extends SQLAdapter<T>> clazz) {
        return this.resultManyQuery(sql, statement -> {}, clazz);
    }

}
