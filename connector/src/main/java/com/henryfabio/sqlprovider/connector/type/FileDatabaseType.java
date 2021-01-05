package com.henryfabio.sqlprovider.connector.type;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import lombok.Getter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * @author Henry Fábio
 */
@Getter
public abstract class FileDatabaseType extends SQLDatabaseType {

    private final File file;

    public FileDatabaseType(String driverClassName, String jdbcUrl, File file) {
        super(driverClassName, jdbcUrl);
        this.file = file;
    }

    @Override
    public SQLConnector connect() {
        try {
            Class.forName(this.getDriverClassName());
            Connection connection = DriverManager.getConnection(this.getJdbcUrl());
            return new SQLConnector(this) {

                @Override
                public void consumeConnection(Consumer<Connection> consumer) {
                    consumer.accept(connection);
                }

            };
        } catch (SQLException | ClassNotFoundException t) {
            t.printStackTrace();
            throw new NullPointerException("connection can't be null");
        }
    }

}
