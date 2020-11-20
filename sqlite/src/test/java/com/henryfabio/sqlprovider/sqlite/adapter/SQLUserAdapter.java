package com.henryfabio.sqlprovider.sqlite.adapter;

import com.henryfabio.sqlprovider.common.adapter.SQLAdapter;
import com.henryfabio.sqlprovider.common.result.SimpleResultSet;
import com.henryfabio.sqlprovider.sqlite.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class SQLUserAdapter implements SQLAdapter<User> {

    @Override
    public @NotNull User adaptResult(@NotNull SimpleResultSet resultSet) throws NullPointerException {
        return new User(UUID.fromString(resultSet.get("uniqueId")));
    }

    @Override
    public void adaptStatement(@NotNull PreparedStatement statement, @NotNull User target) throws SQLException {
        statement.setString(1, target.getUniqueId().toString());
    }

}
