package com.henryfabio.sqlprovider.sqlite;

import com.henryfabio.sqlprovider.common.executor.SQLExecutor;
import com.henryfabio.sqlprovider.sqlite.adapter.SQLUserAdapter;
import com.henryfabio.sqlprovider.sqlite.model.User;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public class SQLiteProviderTest {

    @Test
    public void connect() {
        SQLiteProvider provider = SQLiteProviderFactory.factory();
        provider.connect();

        assertTrue(provider.isConnected());
    }

    @Test
    public void executor() {
        SQLiteProvider provider = SQLiteProviderFactory.factory();
        provider.connect();

        SQLExecutor executor = provider.executor();
        executor.updateQuery("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY AUTOINCREMENT);");
        executor.updateOneQuery(
                "INSERT INTO test(uniqueId) VALUES(?);",
                SQLUserAdapter.class,
                new User(UUID.randomUUID())
        );

        List<User> users = executor.resultManyQuery("SELECT * FROM test;", SQLUserAdapter.class);

        assertFalse(users.isEmpty());
        System.out.println("users: " + users);
    }

}