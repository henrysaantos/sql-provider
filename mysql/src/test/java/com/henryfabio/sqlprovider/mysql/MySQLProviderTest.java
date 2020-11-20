package com.henryfabio.sqlprovider.mysql;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public class MySQLProviderTest {

    @Test
    public void connect() {
        MySQLProvider provider = MySQLProviderFactory.factory();
        provider.connect();
    }

}