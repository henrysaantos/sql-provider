package com.henryfabio.sqlprovider.mysql;

import com.henryfabio.sqlprovider.common.SQLProvider;
import com.henryfabio.sqlprovider.common.executor.SQLExecutor;
import com.henryfabio.sqlprovider.common.executor.impl.SQLExecutorImpl;
import com.henryfabio.sqlprovider.mysql.configuration.MySQLConfiguration;
import com.zaxxer.hikari.HikariDataSource;
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
public final class MySQLProvider implements SQLProvider {

    @Accessors(fluent = true)
    private final SQLExecutor executor = new SQLExecutorImpl(this);

    private final MySQLConfiguration configuration;
    private final HikariDataSource dataSource = new HikariDataSource();

    @Override
    public void connect() {
        this.dataSource.setUsername(this.configuration.get("username"));
        this.dataSource.setPassword(this.configuration.get("password"));
        this.dataSource.setJdbcUrl(
                "jdbc:mysql://" + this.configuration.get("address") + "/" + this.configuration.get("database")
        );
    }

    @Override
    public void disconnect() {
        this.dataSource.close();
    }

    @Override
    public boolean isConnected() {
        return dataSource.isRunning();
    }

    @Override
    public void consumeConnection(Consumer<Connection> consumer) {
        try (Connection connection = this.dataSource.getConnection()) {
            consumer.accept(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
