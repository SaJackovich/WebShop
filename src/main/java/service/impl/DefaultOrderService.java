package service.impl;

import container.OrderStatus;
import db.dao.transaction.TransactionManager;
import db.repository.Repository;
import entity.CartProduct;
import entity.Order;
import entity.OrderInformation;
import service.OrderService;

import java.sql.Date;
import java.util.List;

public class DefaultOrderService implements OrderService {

    private static final String ORDER_WAS_CREATED = "Order was created";

    private TransactionManager transactionManager;
    private Repository<Order> repository;

    public DefaultOrderService(TransactionManager transactionManager, Repository<Order> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public void add(List<CartProduct> cartProducts, OrderInformation orderInformation) {
        transactionManager.doInTransaction(connection -> {
                    repository.save(connection, createOrder(cartProducts, orderInformation));
                    return null;
                });
    }

    private Order createOrder(List<CartProduct> cartProducts, OrderInformation orderInformation){
        return new Order.OrderBuilder().cartProducts(cartProducts)
                .userId(orderInformation.getUserId())
                .orderStatus(OrderStatus.ACCEPTED)
                .date(new Date(System.currentTimeMillis()))
                .statusDetailing(ORDER_WAS_CREATED)
                .orderInformation(orderInformation)
                .build();
    }

}
