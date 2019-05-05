package db.specification.impl;

import db.specification.SQLSpecification;

import java.util.Collections;
import java.util.List;

public class AllManufacturerSpecification implements SQLSpecification {

    private static final String SELECT_FROM_MANUFACTURER = "select * from manufacturer";
    private static final String SELECT_COUNT_FROM_MANUFACTURER = "select count(*) from manufacturer";

    @Override
    public String toMySqlQuery() {
        return SELECT_FROM_MANUFACTURER;
    }

    @Override
    public String countMySqlQuery() {
        return SELECT_COUNT_FROM_MANUFACTURER;
    }

    @Override
    public List<Object> getParams() {
        return Collections.emptyList();
    }
}
