package com.henryfabio.sqlprovider.connector;

import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.SQLiteDatabaseType;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Henry Fábio
 */
public class ConnectorTest {

    public static void main(String[] args) {
        SQLConnector sqlConnector = sqliteDatabaseType().connect();

        sqlConnector.consumeConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("SELECT 1")) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("select query executed with successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private static SQLDatabaseType sqliteDatabaseType() {
        return SQLiteDatabaseType.builder()
                .file(new File("databases/database.db"))
                .build();
    }

    private static SQLDatabaseType mysqlDatabaseType() {
        return MySQLDatabaseType.builder()
                .address("localhost:3306")
                .username("root")
                .password("")
                .database("test")
                .build();
    }

}
