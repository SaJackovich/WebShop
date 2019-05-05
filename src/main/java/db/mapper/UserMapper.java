package db.mapper;

import container.ControllerConstant;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User extract(ResultSet resultSet) throws SQLException {
        User user = new User.UserBuilder().setEmail(resultSet.getString(ControllerConstant.EMAIL))
                .setFirstName(resultSet.getString(ControllerConstant.FIRST_NAME))
                .setLastName(resultSet.getString(ControllerConstant.DB_LAST_NAME))
                .setLogin(resultSet.getString(ControllerConstant.LOGIN))
                .setPassword(resultSet.getString(ControllerConstant.PASSWORD))
                .setIdRole(resultSet.getInt(ControllerConstant.ID_ROLE))
                .build();
        user.setAvatarFullname(resultSet.getString(ControllerConstant.AVATAR));
        return user;
    }

}
