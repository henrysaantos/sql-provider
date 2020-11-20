package com.henryfabio.sqlprovider.sqlite;

import com.henryfabio.sqlprovider.sqlite.configuration.SQLiteConfiguration;

import java.io.File;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class SQLiteProviderFactory {

    public static SQLiteProvider factory() {
        SQLiteConfiguration configuration = new SQLiteConfiguration()
                .file(new File("sqlite", "database.db"));

        return new SQLiteProvider(configuration);
    }

}
