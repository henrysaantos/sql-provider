package com.henryfabio.sqlprovider.connector.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * @author Henry Fábio
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void createFileIfNotExists(File file) {
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
