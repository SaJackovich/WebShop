package service.impl;

import db.dao.transaction.TransactionManager;
import db.repository.Repository;
import db.specification.SQLSpecification;
import db.specification.impl.AllCategorySpecification;
import entity.Category;
import service.CategoryService;

import java.util.List;

public class DefaultCategoryService implements CategoryService {

    private TransactionManager transactionManager;
    private Repository<Category> repository;

    public DefaultCategoryService(TransactionManager transactionManager, Repository<Category> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() {
        SQLSpecification specification = new AllCategorySpecification();
        return transactionManager.doInTransaction(connection ->
                repository.query(connection, specification));
    }
}
