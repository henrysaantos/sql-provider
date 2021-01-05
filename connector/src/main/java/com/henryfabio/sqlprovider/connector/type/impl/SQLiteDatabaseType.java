package com.henryfabio.sqlprovider.connector.type.impl;

import com.henryfabio.sqlprovider.connector.type.FileDatabaseType;
import com.henryfabio.sqlprovider.connector.utils.FileUtils;
import lombok.Builder;

import java.io.File;

/**
 * @author Henry Fábio
 */
public final class SQLiteDatabaseType extends FileDatabaseType {

    @Builder
    public SQLiteDatabaseType(File file) {
        super(
                "org.sqlite.JDBC",
                "jdbc:sqlite:" + file,
                file
        );
        FileUtils.createFileIfNotExists(file);
    }

}
