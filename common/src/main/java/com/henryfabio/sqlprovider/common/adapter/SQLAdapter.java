package com.henryfabio.sqlprovider.common.adapter;

import com.henryfabio.sqlprovider.common.result.SimpleResultSet;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface SQLAdapter<T> {

    @NotNull
    T adaptResult(@NotNull SimpleResultSet resultSet) throws NullPointerException;

    void adaptStatement(@NotNull PreparedStatement statement, @NotNull T target) throws SQLException;

}
