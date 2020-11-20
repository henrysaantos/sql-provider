package com.henryfabio.sqlprovider.sqlite.configuration;

import com.henryfabio.sqlprovider.common.configuration.SQLConfiguration;

import java.io.File;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class SQLiteConfiguration extends SQLConfiguration {

    public SQLiteConfiguration file(File file) {
        return insert("file", file);
    }

}
