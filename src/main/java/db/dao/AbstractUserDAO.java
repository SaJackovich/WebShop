package db.dao;

import entity.User;

import java.sql.Connection;
import java.util.Optional;

public abstract class AbstractUserDAO extends AbstractDAO<User> {

    public abstract boolean containsUserByLogin(Connection connection, String login);

    public abstract Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password);
}
