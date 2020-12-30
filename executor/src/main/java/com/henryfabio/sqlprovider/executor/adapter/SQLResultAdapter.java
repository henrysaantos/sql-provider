package com.henryfabio.sqlprovider.executor.adapter;

import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

import java.sql.SQLException;

/**
 * @author Henry Fábio
 */
public interface SQLResultAdapter<T> {

    T adaptResult(SimpleResultSet resultSet);

}
