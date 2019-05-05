package db.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO<A> implements DAO<A>{

    protected final Logger log = Logger.getLogger(this.getClass());
    private static final String RESULT_SET_CLOSED = "Result set was closed";

    protected void resultSetClose(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(RESULT_SET_CLOSED);
            }
        }
    }
}
