package com.henryfabio.sqlprovider.common.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public abstract class SQLConfiguration {

    private final Map<String, Object> propertyMap = new LinkedHashMap<>();

    public <T extends SQLConfiguration> T insert(String key, Object value) {
        propertyMap.put(key, value);
        return (T) this;
    }

    public <T> T get(String key) {
        return (T) propertyMap.get(key);
    }

}
