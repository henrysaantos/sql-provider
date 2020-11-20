package com.henryfabio.sqlprovider.common.result;

import com.henryfabio.sqlprovider.common.adapter.SQLAdapter;
import com.henryfabio.sqlprovider.common.adapter.provider.SQLAdapterProvider;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@RequiredArgsConstructor
public final class SimpleResultSet {

    private final ResultSet resultSet;

    public <T> T get(String column) {
        try {
            return (T) resultSet.getObject(column);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException("\"" + column + "\" no has element");
        }
    }

    public <T> T adapt(Class<? extends SQLAdapter<T>> clazz) {
        SQLAdapterProvider adapterProvider = SQLAdapterProvider.getInstance();
        return adapterProvider.get(clazz).adaptResult(this);
    }

    public boolean next() {
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
