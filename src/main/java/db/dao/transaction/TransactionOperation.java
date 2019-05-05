package db.dao.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionOperation<T> {

    T produce (Connection connection) throws SQLException;
}
