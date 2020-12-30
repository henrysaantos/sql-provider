package com.henryfabio.sqlprovider.connector.type.impl;

import com.henryfabio.sqlprovider.connector.type.FileDatabaseType;
import lombok.Builder;

import java.io.File;

/**
 * @author Henry Fábio
 */
public final class SQLiteDatabaseType extends FileDatabaseType {

    @Builder
    public SQLiteDatabaseType(File file) {
        super(file);
        createFileIfNotExists();

        driverClassName("org.sqlite.JDBC");
        jdbcUrl("jdbc:sqlite:" + file);
    }

}
