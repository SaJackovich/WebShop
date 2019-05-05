package service;

import entity.CartProduct;
import entity.OrderInformation;

import java.util.List;

public interface OrderService {

    void add(List<CartProduct> cartProducts, OrderInformation orderInformation);

}

