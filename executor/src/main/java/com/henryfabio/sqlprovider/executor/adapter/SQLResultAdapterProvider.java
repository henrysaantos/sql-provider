package com.henryfabio.sqlprovider.executor.adapter;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Henry Fábio
 */
public final class SQLResultAdapterProvider {

    @Getter private static final SQLResultAdapterProvider instance = new SQLResultAdapterProvider();

    private final Map<Class, SQLResultAdapter> adapterMap = new LinkedHashMap<>();

    public <T extends SQLResultAdapter> T getAdapter(Class<T> clazz) {
        return (T) adapterMap.computeIfAbsent(clazz, k -> {
            try {
                return (SQLResultAdapter) k.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new NullPointerException("adapter can't be null");
            }
        });
    }

}
