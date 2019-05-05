package service;

import db.dao.AbstractUserDAO;
import db.dao.transaction.TransactionManager;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public abstract class UserService extends AbstractService<User>{

    protected final AbstractUserDAO userDAO;

    public UserService(TransactionManager manager, AbstractUserDAO dataSource) {
        super(manager, dataSource);
        userDAO = dataSource;
    }

    public abstract Optional<User> add(HttpServletRequest request, String avatarFilename);

    public abstract boolean containsLogin(String login);

    public abstract Optional<User> getUserByLoginAndPassword(String login, String password);

}
