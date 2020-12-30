package com.henryfabio.sqlprovider.connector.type;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public abstract class SQLDatabaseType {

    private final HikariDataSource dataSource = new HikariDataSource();

    public <T extends SQLDatabaseType> T driverClassName(String driverClassName) {
        return configureDataSource(HikariConfig::setDriverClassName, driverClassName);
    }

    public <T extends SQLDatabaseType> T jdbcUrl(String jdbcUrl) {
        return configureDataSource(HikariConfig::setJdbcUrl, jdbcUrl);
    }

    public <T extends SQLDatabaseType, U> T configureDataSource(BiConsumer<HikariDataSource, U> consumer, U value) {
        consumer.accept(dataSource, value);
        return (T) this;
    }

    public SQLConnector connector() {
        return new SQLConnector(this);
    }

}
