package com.henryfabio.sqlprovider.connector.type.impl;

import com.henryfabio.sqlprovider.connector.type.RemoteDatabaseType;
import lombok.Builder;

/**
 * @author Henry Fábio
 */
public final class MySQLDatabaseType extends RemoteDatabaseType {

    @Builder
    public MySQLDatabaseType(String address, String username, String password, String database) {
        super(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://" + address + "/" + database,
                username,
                password
        );
    }

}
