package com.henryfabio.sqlprovider.connector.type.impl;

import com.henryfabio.sqlprovider.connector.type.RemoteDatabaseType;
import com.zaxxer.hikari.HikariConfig;
import lombok.Builder;

/**
 * @author Henry Fábio
 */
public final class MySQLDatabaseType extends RemoteDatabaseType {

    @Builder
    public MySQLDatabaseType(String address, String username, String password, String database) {
        super(address, username, password, database);
        configureDataSource(HikariConfig::setUsername, username);
        configureDataSource(HikariConfig::setPassword, password);

        driverClassName("com.mysql.jdbc.Driver");
        jdbcUrl("jdbc:mysql://" + address + "/" + database);
    }

}
