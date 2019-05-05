package db.specification.impl;

import container.MysqlProductFields;
import db.builder.SelectBuilder;
import db.specification.SQLSpecification;
import entity.ProductFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilteredProductSpecification implements SQLSpecification {

    private static final String INNER_JOIN_CATEGORY_ON_PRODUCT_ID_CATEGORY_CATEGORY_ID =
            "inner join `category` on product.id_category=category.id ";
    private static final String INNER_JOIN_MANUFACTURER_ON_PRODUCT_ID_MANUFACTURER_MANUFACTURER_ID =
            "inner join `manufacturer` on product.id_manufacturer=manufacturer.id ";
    private static final String ORDER_BY = "order by ";
    private static final String PRODUCT_NAME_LIKE = "product.name like '";
    private static final String PRODUCT_PRICE_BETWEEN = "product.price between ";
    private static final String AND = " and ";
    private static final String PRODUCT_ID_MANUFACTURER = "product.id_manufacturer=";
    private static final String PRODUCT_ID_CATEGORY = "product.id_category=";
    private static final String COUNT = "count(*) ";

    private List<Object> parameters;
    private ProductFilter productFilter;
    private SelectBuilder subQueryBuilder;

    public FilteredProductSpecification(ProductFilter productFilter) {
        this.productFilter = productFilter;
    }

    @Override
    public String toMySqlQuery() {
        parameters = new ArrayList<>();
        subQueryBuilder = initStandartQuery();
        addProductIds();
        addInnerJoin();
        addName();
        addCategories();
        addManufacturer();
        addPrice();
        addOrderBy();
        addLimit();
        addOffset();
        parameters.addAll(subQueryBuilder.getParameters());
        return subQueryBuilder.productsBuild();
    }

    private void addProductIds() {
        List<Integer> productIds = productFilter.getProductIds();
        if (Objects.nonNull(productIds) && !productIds.isEmpty()) {
            String whereQuery = getQuery(productIds);
            subQueryBuilder.where(whereQuery);
        }
    }

    private String getQuery(List<Integer> productIds) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < productIds.size(); i++) {
            builder.append("product.id=").append(productIds.get(i)).append(" ");
            if (i != productIds.size() - 1) {
                builder.append("or ");
            }
        }
        return builder.toString();
    }

    private void addInnerJoin() {
        subQueryBuilder.innerJoin(INNER_JOIN_CATEGORY_ON_PRODUCT_ID_CATEGORY_CATEGORY_ID)
                .innerJoin(INNER_JOIN_MANUFACTURER_ON_PRODUCT_ID_MANUFACTURER_MANUFACTURER_ID);
    }

    private void addOffset() {
        if (Objects.nonNull(productFilter.getPage()) && productFilter.getPage() > 1){
            subQueryBuilder.offset((productFilter.getPage() - 1) * productFilter.getProductLimit());
        } else {
            subQueryBuilder.offset(productFilter.getDefaultOffset());
        }
    }

    private void addLimit() {
        if (Objects.nonNull(productFilter.getProductLimit())){
            subQueryBuilder.limit(productFilter.getProductLimit());
        } else {
            subQueryBuilder.limit(productFilter.getDefaultProductLimit());
        }
    }

    private void addOrderBy() {
        if (Objects.nonNull(productFilter.getOrderBy())){
                subQueryBuilder.orderBy(ORDER_BY + productFilter.getOrderBy() + " " + productFilter.getOrderByKind() + " ");
        }
    }

    private void addName() {
        if (Objects.nonNull(productFilter.getName())){
            subQueryBuilder.where(PRODUCT_NAME_LIKE + productFilter.getName() + "%' ");
        }
    }

    private void addPrice() {
        if (Objects.nonNull(productFilter.getMaxPrice()) && Objects.nonNull(productFilter.getMinPrice())){
            subQueryBuilder.where(PRODUCT_PRICE_BETWEEN + productFilter.getMinPrice() + AND + productFilter.getMaxPrice() + " ");
        }
    }

    private void addManufacturer() {
        if (Objects.nonNull(productFilter.getManufacturers())){
            for (Integer manufacturerId: productFilter.getManufacturers()){
                subQueryBuilder.where(PRODUCT_ID_MANUFACTURER + manufacturerId + " ");
            }
        }
    }

    private void addCategories() {
        if (Objects.nonNull(productFilter.getCategories())){
            for (Integer categoryid: productFilter.getCategories()) {
                subQueryBuilder.where(PRODUCT_ID_CATEGORY + categoryid + " ");
            }
        }
    }

    private SelectBuilder initStandartQuery() {
        return new SelectBuilder().select(MysqlProductFields.PRODUCT_ID)
                .select(MysqlProductFields.PRODUCT_NAME)
                .select(MysqlProductFields.PRODUCT_IMAGE)
                .select(MysqlProductFields.PRODUCT_DESCRIPTION)
                .select(MysqlProductFields.PRODUCT_PRICE)
                .select(MysqlProductFields.PRODUCT_QUANTITY)
                .select(MysqlProductFields.PRODUCT_ID_MANUFACTURER1)
                .select(MysqlProductFields.MANUFACTURER_NAME)
                .select(MysqlProductFields.PRODUCT_ID_CATEGORY1)
                .select(MysqlProductFields.CATEGORY_NAME)
                .select(MysqlProductFields.CATEGORY_ID)
                .select(MysqlProductFields.MANUFACTURER_ID);
    }

    @Override
    public String countMySqlQuery() {
        parameters = new ArrayList<>();
        subQueryBuilder = new SelectBuilder().select(COUNT);
        addName();
        addCategories();
        addManufacturer();
        addPrice();
        parameters.addAll(subQueryBuilder.getParameters());
        return subQueryBuilder.productCountBuild();
    }

    @Override
    public List<Object> getParams() {
        return parameters;
    }

}
