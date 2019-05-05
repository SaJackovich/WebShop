package service;

import db.dao.AbstractDAO;
import db.dao.transaction.TransactionManager;
import entity.Avatar;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public abstract class AvatarService extends AbstractService<Avatar> {

    protected AbstractDAO<Avatar> avatarDAO;

    public AvatarService(TransactionManager manager, AbstractDAO<Avatar> dao) {
        super(manager, dao);
        avatarDAO = dao;
    }

    public abstract Optional<Avatar> create(HttpServletRequest request);

}
