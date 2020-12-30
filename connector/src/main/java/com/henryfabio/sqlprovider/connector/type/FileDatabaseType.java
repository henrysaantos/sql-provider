package com.henryfabio.sqlprovider.connector.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public abstract class FileDatabaseType extends SQLDatabaseType {

    private final File file;

    public void createFileIfNotExists() {
        try {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
