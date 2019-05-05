package db;

import db.mapper.Mapper;
import db.specification.SQLSpecification;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class JdbcTemplate<A> {

    private Mapper<A> mapper;
    private String countQuery;
    private final Logger log = Logger.getLogger(this.getClass());

    private static final String RESULT_SET_CLOSED = "Result set was closed";

    public JdbcTemplate(Mapper<A> mapper) {
        this.mapper = mapper;
    }

    public List<A> getAllBySpecification(Connection connection, SQLSpecification sqlSpecification) throws SQLException {
        if (Objects.nonNull(sqlSpecification.getParams())){
            return getByQueryAndParameters(connection, sqlSpecification);
        } else {
            return getOnlyByQuery(connection, sqlSpecification);
        }
    }

    public int getCountBySpecification(Connection connection, SQLSpecification specification) throws SQLException {
        countQuery = specification.countMySqlQuery();
        return getCountWithoutParameters(connection);
    }

    private int getCountWithoutParameters(Connection connection) throws SQLException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(countQuery)) {
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } finally {
            resultSetClose(resultSet);
        }
        return 0;
    }

    private List<A> getOnlyByQuery(Connection connection, SQLSpecification sqlSpecification) throws SQLException {
        List<A> entities = new ArrayList<>();
        ResultSet resultSet = null;
        String query = sqlSpecification.toMySqlQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                A entity = mapper.extract(resultSet);
                entities.add(entity);
            }
        } finally {
            resultSetClose(resultSet);
        }
        return entities;
    }

    private List<A> getByQueryAndParameters(Connection connection, SQLSpecification sqlSpecification) throws SQLException {
        ResultSet resultSet = null;
        List<A> entities = new ArrayList<>();
        String query = sqlSpecification.toMySqlQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            mapParameters(statement, sqlSpecification.getParams());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                A entity = mapper.extract(resultSet);
                entities.add(entity);
            }
        } finally {
            resultSetClose(resultSet);
        }
        return entities;
    }

    private void mapParameters(PreparedStatement ps, List<Object> params) throws SQLException {
        int i = 1;
        for (Object param : params) {
            if (param instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) param).getTime()));
            } else if (param instanceof Integer) {
                ps.setInt(i++, (Integer) param);
            } else if (param instanceof Long) {
                ps.setLong(i++, (Long) param);
            } else if (param instanceof Double) {
                ps.setDouble(i++, (Double) param);
            } else if (param instanceof Float) {
                ps.setFloat(i++, (Float) param);
            } else if (param instanceof BigDecimal) {
                ps.setBigDecimal(i++, (BigDecimal) param);
            } else {
                ps.setString(i++, (String) param);
            }
        }
    }

    private void resultSetClose(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(RESULT_SET_CLOSED);
            }
        }
    }
}
