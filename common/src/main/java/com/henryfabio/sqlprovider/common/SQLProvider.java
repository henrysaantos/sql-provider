package com.henryfabio.sqlprovider.common;

import com.henryfabio.sqlprovider.common.configuration.SQLConfiguration;
import com.henryfabio.sqlprovider.common.executor.SQLExecutor;

import java.sql.Connection;
import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface SQLProvider {

    void connect();

    void disconnect();

    boolean isConnected();

    void consumeConnection(Consumer<Connection> consumer);

    SQLConfiguration getConfiguration();

    SQLExecutor executor();

}
