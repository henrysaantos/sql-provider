package com.henryfabio.sqlprovider.executor;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.SQLiteDatabaseType;

import java.io.File;

/**
 * @author Henry Fábio
 */
public class ExecutorTest {

    public static void main(String[] args) {
        SQLConnector sqlConnector = sqliteDatabaseType().connect();
        SQLExecutor sqlExecutor = new SQLExecutor(sqlConnector);

        sqlExecutor.updateQuery("CREATE TABLE IF NOT EXISTS users(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(32) NOT NULL UNIQUE" +
                ")");

/*        sqlExecutor.updateQuery("INSERT INTO users(name) VALUES(?)", statement -> {
            try {
                statement.setString(1, "Henry");
            } catch (SQLException t) {
                t.printStackTrace();
            }
        });*/

        System.out.println(sqlExecutor.resultManyQuery(
                "SELECT * FROM users WHERE name = ?",
                statement -> statement.set(1, "Henry"),
                TestResultAdapter.class)
        );
    }

    private static SQLDatabaseType sqliteDatabaseType() {
        return SQLiteDatabaseType.builder()
                .file(new File("databases/database.db"))
                .build();
    }

}
