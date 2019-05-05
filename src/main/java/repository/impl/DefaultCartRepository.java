package repository.impl;

import entity.Product;
import repository.CartRepository;

import java.util.HashMap;
import java.util.Map;

public class DefaultCartRepository implements CartRepository<Product> {

    private Map<Product, Integer> cart;

    public DefaultCartRepository() {
        cart = new HashMap<>();
    }

    @Override
    public int get(Product product) {
        return cart.get(product);
    }

    @Override
    public void addAll(Map<Product, Integer> products) {
        cart.putAll(products);
    }

    @Override
    public int size() {
        return cart.size();
    }

    @Override
    public boolean contains(int key) {
        return cart.containsValue(key);
    }

    @Override
    public void remove(Product product) {
        cart.remove(product);
    }

    @Override
    public void removeAll() {
        cart = new HashMap<>();
    }

    @Override
    public Map<Product, Integer> getAll() {
        return cart;
    }
}
