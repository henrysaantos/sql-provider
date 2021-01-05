package com.henryfabio.sqlprovider.connector.type;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public abstract class SQLDatabaseType {

    private final String driverClassName;
    private final String jdbcUrl;

    public abstract SQLConnector connect();

}
