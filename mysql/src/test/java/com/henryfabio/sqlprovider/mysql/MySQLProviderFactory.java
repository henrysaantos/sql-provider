package com.henryfabio.sqlprovider.mysql;

import com.henryfabio.sqlprovider.mysql.configuration.MySQLConfiguration;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class MySQLProviderFactory {

    public static MySQLProvider factory() {
        MySQLConfiguration configuration = new MySQLConfiguration()
                .username("root")
                .password("")
                .address("localhost:3306")
                .database("test");

        return new MySQLProvider(configuration);
    }

}
