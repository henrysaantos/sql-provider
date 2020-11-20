package com.henryfabio.sqlprovider.sqlite;

import com.henryfabio.sqlprovider.common.SQLProvider;
import com.henryfabio.sqlprovider.common.executor.SQLExecutor;
import com.henryfabio.sqlprovider.common.executor.impl.SQLExecutorImpl;
import com.henryfabio.sqlprovider.sqlite.configuration.SQLiteConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Getter
@RequiredArgsConstructor
public final class SQLiteProvider implements SQLProvider {

    @Accessors(fluent = true)
    private final SQLExecutor executor = new SQLExecutorImpl(this);

    private final SQLiteConfiguration configuration;
    private Connection connection;

    @Override
    public void connect() {
        try {
            File databaseFile = configuration.get("file");
            if (!databaseFile.exists()) {
                databaseFile.getParentFile().mkdirs();
                databaseFile.createNewFile();
            }

            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (!isConnected()) return;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void consumeConnection(Consumer<Connection> consumer) {
        consumer.accept(this.connection);
    }

}
