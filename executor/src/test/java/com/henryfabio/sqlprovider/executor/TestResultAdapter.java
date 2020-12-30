package com.henryfabio.sqlprovider.executor;

import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

/**
 * @author Henry Fábio
 */
public final class TestResultAdapter implements SQLResultAdapter<Integer> {

    @Override
    public Integer adaptResult(SimpleResultSet resultSet) {
        return resultSet.get("id");
    }

}
