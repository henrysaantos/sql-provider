package com.henryfabio.sqlprovider.common.adapter.provider;

import com.henryfabio.sqlprovider.common.adapter.SQLAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLAdapterProvider {

    @Getter private static final SQLAdapterProvider instance = new SQLAdapterProvider();

    private final Map<Class<? extends SQLAdapter>, SQLAdapter> adapterMap = new LinkedHashMap<>();

    public <T> SQLAdapter<T> get(Class<? extends SQLAdapter<T>> clazz) {
        return adapterMap.computeIfAbsent(clazz, this::createAdapter);
    }

    private SQLAdapter createAdapter(Class<? extends SQLAdapter> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
