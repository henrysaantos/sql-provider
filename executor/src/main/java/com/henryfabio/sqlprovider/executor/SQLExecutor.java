package com.henryfabio.sqlprovider.executor;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapterProvider;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;
import com.henryfabio.sqlprovider.executor.statement.SimpleStatement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public final class SQLExecutor {

    private final SQLConnector sqlConnector;

    public void updateQuery(String query, Consumer<SimpleStatement> consumer) {
        sqlConnector.consumeConnection(connection -> {
            try (SimpleStatement statement = SimpleStatement.of(connection.prepareStatement(query))) {
                consumer.accept(statement);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateQuery(String query) {
        updateQuery(query, preparedStatement -> {});
    }

    public <T> T resultQuery(String query, Consumer<SimpleStatement> consumer, Function<SimpleResultSet, T> function) {
        AtomicReference<T> value = new AtomicReference<>();
        sqlConnector.consumeConnection(connection -> {
            try (SimpleStatement statement = SimpleStatement.of(connection.prepareStatement(query))) {
                consumer.accept(statement);

                try (SimpleResultSet resultSet = statement.executeQuery()) {
                    value.set(function.apply(resultSet));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return value.get();
    }

    public <T> T resultOneQuery(String query,
                                Consumer<SimpleStatement> consumer,
                                Class<? extends SQLResultAdapter<T>> resultAdapterClass
    ) {
        return resultQuery(query, consumer, resultSet -> {
            if (resultSet.next()) {
                SQLResultAdapterProvider adapterProvider = SQLResultAdapterProvider.getInstance();
                SQLResultAdapter<T> adapter = adapterProvider.getAdapter(resultAdapterClass);
                return adapter.adaptResult(resultSet);
            }

            return null;
        });
    }

    public <T> Set<T> resultManyQuery(String query,
                                      Consumer<SimpleStatement> consumer,
                                      Class<? extends SQLResultAdapter<T>> resultAdapterClass
    ) {
        return this.resultQuery(query, consumer, resultSet -> {
            SQLResultAdapterProvider adapterProvider = SQLResultAdapterProvider.getInstance();
            SQLResultAdapter<T> adapter = adapterProvider.getAdapter(resultAdapterClass);

            Set<T> elements = new LinkedHashSet<>();
            while (resultSet.next()) {
                elements.add(adapter.adaptResult(resultSet));
            }

            return elements;
        });
    }

}
