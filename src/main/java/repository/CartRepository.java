package repository;

import entity.Product;

import java.util.Map;

public interface CartRepository<K> {

    boolean contains(int id);

    void remove(K key);

    void removeAll();

    int get(Product product);

    void addAll(Map<K, Integer> products);

    int size();

    Map<K, Integer> getAll();

}
