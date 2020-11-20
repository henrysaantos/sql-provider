package com.henryfabio.sqlprovider.mysql.configuration;

import com.henryfabio.sqlprovider.common.configuration.SQLConfiguration;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class MySQLConfiguration extends SQLConfiguration {

    public MySQLConfiguration username(String username) {
        return insert("username", username);
    }

    public MySQLConfiguration password(String password) {
        return insert("password", password);
    }

    public MySQLConfiguration address(String address) {
        return insert("address", address);
    }

    public MySQLConfiguration database(String database) {
        return insert("database", database);
    }

}
