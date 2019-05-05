package db.dao;

import java.sql.Connection;
import java.util.Optional;

public interface DAO<A> {

    void add(Connection connection, A object);

    Optional<A> getById(Connection connection, int id);

}
