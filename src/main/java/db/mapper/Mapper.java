package db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<A> {

    A extract(ResultSet resultSet) throws SQLException;

}
