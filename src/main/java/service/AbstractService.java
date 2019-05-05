package service;

import db.dao.DAO;
import db.dao.transaction.TransactionManager;

public abstract class AbstractService<A> implements Service<A> {

    protected DAO dao;
    protected TransactionManager transactionManager;

    AbstractService(TransactionManager transactionManager, DAO<A> dao) {
        this.dao = dao;
        this.transactionManager = transactionManager;
    }

}
