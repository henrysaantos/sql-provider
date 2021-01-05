package com.henryfabio.sqlprovider.connector.type;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * @author Henry Fábio
 */
@Getter
public abstract class RemoteDatabaseType extends SQLDatabaseType {

    private final HikariDataSource dataSource = new HikariDataSource();

    public RemoteDatabaseType(
            String driverClassName, String jdbcUrl, String username, String password
    ) {
        super(driverClassName, jdbcUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(jdbcUrl);
    }

    public <T extends RemoteDatabaseType> T configureDataSource(Consumer<HikariDataSource> consumer) {
        consumer.accept(dataSource);
        return (T) this;
    }

    @Override
    public SQLConnector connect() {
        return new SQLConnector(this) {

            @Override
            public void consumeConnection(Consumer<Connection> consumer) {
                try (Connection connection = dataSource.getConnection()) {
                    consumer.accept(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        };
    }

}
