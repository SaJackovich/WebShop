package db.dao.transaction;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private final Logger log = Logger.getLogger(this.getClass());
    private BasicDataSource dataSource;

    private static final String CONNECTION_WASN_T_CLOSED = "Connection wasn't closed";
    private static final String CHANGES_NOT_SAVED = "Changes wasn't save";
    private static final String CHANGES_NOT_BE_ROLLBACK = "Changes wasn't be rollback";
    private static final String CONNECTION_NOT_CREATED = "Connection wasn't created";

    public TransactionManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T doInTransaction(TransactionOperation<T> operation) {
        T result = null;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            result = operation.produce(connection);
            commitChanges(connection);
        } catch (SQLException e) {
            rollbackChanges(connection);
        }
        closeConnection(connection);
        return result;
    }

    private void closeConnection(Connection connection){
        if (!isConnectionNull(connection)){
            try{
                connection.close();
            } catch (SQLException e){
                log.error(CONNECTION_WASN_T_CLOSED);
            }
        }
    }

    private void commitChanges(Connection connection) {
        if (!isConnectionNull(connection)) {
            try {
                connection.commit();
            } catch (SQLException e) {
                log.error(CHANGES_NOT_SAVED);
            }
        }
    }

    private void rollbackChanges(Connection connection) {
        if (!isConnectionNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error(CHANGES_NOT_BE_ROLLBACK);
            }
        }
    }

    private boolean isConnectionNull(Connection connection){
        if (connection == null) {
            log.error(CONNECTION_NOT_CREATED);
            return true;
        }
        return false;
    }
}
