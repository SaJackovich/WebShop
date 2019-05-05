package service;

import entity.CartProduct;
import entity.Product;
import entity.ProductFilter;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<CartProduct> getProductsByIds(Map<Integer, Integer> ids);

    List<Product> getFilteredProducts(ProductFilter filterBean);

    int getFilteredProductsCount(ProductFilter filterBean);

}
