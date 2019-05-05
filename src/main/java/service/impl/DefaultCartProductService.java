package service.impl;

import entity.CartProduct;
import entity.Product;
import repository.CartRepository;
import service.CartProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultCartProductService implements CartProductService<Product> {

    private CartRepository<Product> repository;

    private BigDecimal decimal;

    public DefaultCartProductService(CartRepository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public void add(List<CartProduct> cartProducts) {
        repository.addAll(cartProducts.stream()
                .collect(Collectors.toMap(CartProduct::getProduct, CartProduct::getAmount)));
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
        return repository.getAll()
                .entrySet()
                .stream()
                .map(entry -> new CartProduct.CartProductBuilder()
                        .product(entry.getKey())
                        .amount(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return repository.size();
    }

    @Override
    public BigDecimal allAmount() {
        decimal = new BigDecimal(0);
        repository.getAll()
                .entrySet()
                .stream()
                .map(x -> new BigDecimal(x.getValue()))
                .forEach(x -> decimal = decimal.add(x));
        return decimal;
    }

    @Override
    public int getCount(Product product) {
        return repository.get(product);
    }
}
