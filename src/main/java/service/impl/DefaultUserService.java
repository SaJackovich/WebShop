package service.impl;

import db.dao.AbstractUserDAO;
import db.dao.transaction.TransactionManager;
import web.bean.RegistrationForm;
import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DefaultUserService extends UserService {

    public DefaultUserService(TransactionManager manager, AbstractUserDAO dao) {
        super(manager, dao);
    }

    @Override
    public Optional<User> add(HttpServletRequest request, String avatarFullname) {
        User user = new RegistrationForm().createUserByRequest(request);
        user.setAvatarFullname(avatarFullname);
        transactionManager.doInTransaction(connection -> {
                    userDAO.add(connection, user);
                    return null;
                });
        return Optional.of(user);
    }

    @Override
    public Optional<User> get(int id) {
        return transactionManager.doInTransaction(connection ->
                    userDAO.getById(connection, id));
    }

    @Override
    public boolean containsLogin(String login) {
        return transactionManager.doInTransaction(connection ->
                    userDAO.containsUserByLogin(connection, login));
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        return transactionManager.doInTransaction(connection ->
                    userDAO.getUserByLoginAndPassword(connection, login, password));
    }


}
