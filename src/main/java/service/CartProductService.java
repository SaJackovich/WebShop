package service;

import entity.CartProduct;
import entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartProductService<A>{

    int getCount(Product product);

    void add(List<CartProduct> cartProducts);

    void remove(A key);

    void removeAll();

    List<CartProduct> getAll();

    int size();

    BigDecimal allAmount();

}
