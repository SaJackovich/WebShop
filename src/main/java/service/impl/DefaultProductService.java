package service.impl;

import db.dao.transaction.TransactionManager;
import db.repository.Repository;
import db.specification.SQLSpecification;
import db.specification.impl.FilteredProductSpecification;
import entity.CartProduct;
import entity.Product;
import entity.ProductFilter;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultProductService implements ProductService {

    private TransactionManager transactionManager;
    private Repository<Product> repository;

    public DefaultProductService(TransactionManager transactionManager, Repository<Product> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<CartProduct> getProductsByIds(Map<Integer, Integer> ids) {
        ProductFilter filter = new ProductFilter();
        filter.setProductIds(new ArrayList<>(ids.keySet()));
        SQLSpecification specification = new FilteredProductSpecification(filter);
        List<Product> products = transactionManager.doInTransaction(connection ->
                    repository.query(connection, specification));
        return getProductWithIds(products, ids);
    }

    private List<CartProduct> getProductWithIds(List<Product> products, Map<Integer, Integer> ids) {
        List<CartProduct> cartProducts = new ArrayList<>();
        for (Product product : products){
            for (Map.Entry<Integer, Integer> idsAndCount : ids.entrySet()){
                if (product.getId() == idsAndCount.getKey()){
                    CartProduct cartProduct = new CartProduct.CartProductBuilder()
                            .amount(idsAndCount.getValue())
                            .product(product)
                            .build();
                    cartProducts.add(cartProduct);
                    break;
                }
            }
        }
        return cartProducts;
    }

    @Override
    public List<Product> getFilteredProducts(ProductFilter filterBean) {
        SQLSpecification specification = new FilteredProductSpecification(filterBean);
        return transactionManager.doInTransaction(connection ->
                    repository.query(connection, specification));
    }

    @Override
    public int getFilteredProductsCount(ProductFilter filterBean) {
        SQLSpecification specification = new FilteredProductSpecification(filterBean);
        return transactionManager.doInTransaction(connection ->
                        repository.getCount(connection, specification));
    }

}
