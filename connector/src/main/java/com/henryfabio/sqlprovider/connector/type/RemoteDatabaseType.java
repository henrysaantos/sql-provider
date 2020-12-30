package com.henryfabio.sqlprovider.connector.type;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public abstract class RemoteDatabaseType extends SQLDatabaseType {

    private final String address;
    private final String username;
    private final String password;
    private final String database;

}
