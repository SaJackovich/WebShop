package service.impl;

import db.dao.transaction.TransactionManager;
import db.repository.Repository;
import db.specification.SQLSpecification;
import db.specification.impl.AllManufacturerSpecification;
import entity.Manufacturer;
import service.ManufacturerService;

import java.util.List;

public class DefaultManufacturerService implements ManufacturerService {

    private TransactionManager transactionManager;
    private Repository<Manufacturer> repository;


    public DefaultManufacturerService(TransactionManager transactionManager, Repository<Manufacturer> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        SQLSpecification specification = new AllManufacturerSpecification();
        return transactionManager.doInTransaction(connection ->
                repository.query(connection, specification));
    }
}
