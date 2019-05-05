package service.impl;

import entity.CartProduct;
import entity.Product;
import repository.CartRepository;
import service.CartProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCartProductService implements CartProductService<Product> {

    private CartRepository<Product> repository;

    public DefaultCartProductService(CartRepository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public void add(List<CartProduct> cartProducts) {
        Map<Product, Integer> products = new HashMap<>();
        for (CartProduct cartProduct : cartProducts){
            products.put(cartProduct.getProduct(), cartProduct.getAmount());
        }
        repository.addAll(products);
    }

    @Override
    public void remove(Product product) {
        repository.remove(product);
    }

    @Override
    public void removeAll() {
        repository.removeAll();
    }

    @Override
    public List<CartProduct> getAll() {
        List<CartProduct> cartProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> product : repository.getAll().entrySet()){
            CartProduct cartProduct = new CartProduct.CartProductBuilder()
                    .product(product.getKey())
                    .amount(product.getValue())
                    .build();
            cartProducts.add(cartProduct);
        }
        return cartProducts;
    }

    @Override
    public int size() {
        return repository.size();
    }

    @Override
    public BigDecimal allAmount() {
        Map<Product, Integer> cartProducts = repository.getAll();
        BigDecimal amount = new BigDecimal(0);
        for (Map.Entry<Product, Integer> product : cartProducts.entrySet()){
            amount = amount.subtract(new BigDecimal(product.getValue()));
        }
        return amount;
    }

    @Override
    public int getCount(Product product) {
        return repository.get(product);
    }
}
