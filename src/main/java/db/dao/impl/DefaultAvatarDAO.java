package db.dao.impl;

import db.dao.AbstractDAO;
import entity.Avatar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DefaultAvatarDAO extends AbstractDAO<Avatar> {

    private static final String INSERT_NEW_AVATAR = "insert into avatar values (?, ?)";
    private static final String GET_AVATAR_BY_ID = "select * from avatar where id=?";

    private static final String USER_DATA_ERROR = "Inability to execute a command by invalid user data";
    private static final String INVALID_AVATAR_ID = "Inability to execute a command by invalid avatar id";

    private static final String AVATAR_ID = "id";
    private static final String AVATAR_NAME = "name";

    @Override
    public void add(Connection connection, Avatar avatar) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_AVATAR)){
            statement.setInt(1, avatar.getId());
            statement.setString(2, avatar.getFullFileName());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(USER_DATA_ERROR, e);
        }
    }

    @Override
    public Optional<Avatar> getById(Connection connection, int id) {
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(GET_AVATAR_BY_ID)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getAvatarFromQuery(resultSet));
            }
        } catch (SQLException e) {
            log.error(INVALID_AVATAR_ID);
        } finally {
            resultSetClose(resultSet);
        }
        return Optional.empty();
    }

    private Avatar getAvatarFromQuery(ResultSet resultSet) throws SQLException {
        Avatar avatar = new Avatar();
        avatar.setId(resultSet.getInt(AVATAR_ID));
        avatar.setFullFileName(resultSet.getString(AVATAR_NAME));
        return avatar;
    }

}
