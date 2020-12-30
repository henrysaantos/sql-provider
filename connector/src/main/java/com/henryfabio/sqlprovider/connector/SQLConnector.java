package com.henryfabio.sqlprovider.connector;

import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * @author Henry Fábio
 */
@RequiredArgsConstructor
public final class SQLConnector {

    private final SQLDatabaseType databaseType;

    public Connection connection() {
        try {
            return databaseType.getDataSource().getConnection();
        } catch (SQLException t) {
            t.printStackTrace();
            return null;
        }
    }

    public void consumeConnection(Consumer<Connection> consumer) {
        try (Connection connection = connection()) {
            consumer.accept(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        databaseType.getDataSource().close();
    }

}
