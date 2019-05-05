package db.specification.impl;

import db.specification.SQLSpecification;

import java.util.Collections;
import java.util.List;

public class AllCategorySpecification implements SQLSpecification {

    private static final String SELECT_FROM_CATEGORY = "select * from category";
    private static final String SELECT_COUNT_FROM_CATEGORY = "select count(*) from category";

    @Override
    public String toMySqlQuery() {
        return SELECT_FROM_CATEGORY;
    }

    @Override
    public String countMySqlQuery() {
        return SELECT_COUNT_FROM_CATEGORY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.emptyList();
    }
}
