package db.dao.impl;

import db.dao.AbstractUserDAO;
import db.mapper.Mapper;
import db.mapper.UserMapper;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DefaultUserDAO extends AbstractUserDAO {

    private static final String INSERT_NEW_USER = "insert into users values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_ID = "select * from users where id=?";
    private static final String GET_USER_BY_LOGIN = "select * from users where login=?";
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "select * from users where login=? and password=?";

    private static final String USER_ADDED = "User was added to database";
    private static final String INVALID_USER_DATA = "Inability to execute a command by invalid user data";
    private static final String INVALID_USER_ID = "Inability to execute a command due to invalid user id";
    private static final String INVALID_USER_LOGIN = "Inability to execute a command due to invalid user login";
    private static final String INVALID_USER_LOGIN_OR_PASSWORD = "Inability to execute a command due to invalid user login or password";
    private static final int USER_ID_ROLE = 2;

    private Mapper<User> userMapper;

    public DefaultUserDAO() {
        userMapper = new UserMapper();
    }

    @Override
    public void add(Connection connection, User user){
        int k = 1;
        try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)){
            statement.setInt(k++, user.getId());
            statement.setString(k++, user.getEmail());
            statement.setString(k++, user.getFirstName());
            statement.setString(k++, user.getLastName());
            statement.setString(k++, user.getLogin());
            statement.setString(k++, user.getPassword());
            statement.setString(k++, user.getAvatarFullname());
            statement.setInt(k, USER_ID_ROLE);
            statement.executeUpdate();
            log.info(USER_ADDED);
        } catch (SQLException e) {
            log.error(INVALID_USER_DATA, e);
        }
    }

    @Override
    public Optional<User> getById(Connection connection, int id){
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return Optional.ofNullable(userMapper.extract(resultSet));
        } catch (SQLException e) {
            log.error(INVALID_USER_ID);
        } finally {
            resultSetClose(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public boolean containsUserByLogin(Connection connection, String login){
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            log.error(INVALID_USER_LOGIN, e);
            return false;
        } finally {
            resultSetClose(resultSet);
        }
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password) {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)){
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(userMapper.extract(resultSet));
            }
        } catch (SQLException e) {
            log.error(INVALID_USER_LOGIN_OR_PASSWORD, e);
        } finally {
            resultSetClose(resultSet);
        }
        return Optional.empty();
    }

}
