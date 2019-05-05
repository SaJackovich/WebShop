package db.repository.impl;

import db.repository.Repository;
import db.specification.SQLSpecification;
import entity.CartProduct;
import entity.Order;
import entity.OrderInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLOrderRepository implements Repository<Order> {

    private static final String INSERT_INTO_ORDER = "insert into `order` values (?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_INTO_ORDER_INFORMATION = "insert into `order_information` values (default, ?, ?, ?, ?)";

    @Override
    public void save(Connection connection, Order order) throws SQLException {
        addOrder(connection, order);
        addOrderInformation(connection, order);
    }

    private void addOrder(Connection connection, Order order) throws SQLException {
        OrderInformation orderInformation = order.getOrderInformation();
        try(PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ORDER)){
            int k = 1;
            statement.setInt(k++, order.getId());
            statement.setInt(k++, order.getOrderStatus().getStatus());
            statement.setString(k++, orderInformation.getPaymentKind());
            statement.setDate(k++, order.getDate());
            statement.setString(k++, orderInformation.getDelivery());
            statement.setLong(k++, orderInformation.getBankCard());
            statement.setInt(k, order.getUserId());
            statement.executeUpdate();
        }
    }

    private void addOrderInformation(Connection connection, Order order) throws SQLException {
        List<CartProduct> cartProducts = order.getCartProducts();
        for (CartProduct cartProduct : cartProducts){
            try(PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ORDER_INFORMATION)){
                int k = 1;
                statement.setInt(k++, order.getId());
                statement.setInt(k++, cartProduct.getProduct().getId());
                statement.setInt(k++, cartProduct.getAmount());
                statement.setBigDecimal(k, cartProduct.getPrice());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Connection connection, Order order) throws SQLException {
        //todo
    }

    @Override
    public void update(Connection connection, Order order) throws SQLException {
        //todo
    }

    @Override
    public List<Order> query(Connection connection, SQLSpecification specification) throws SQLException {
        //todo
        return null;
    }

    @Override
    public int getCount(Connection connection, SQLSpecification specification) throws SQLException {
        //todo
        return 0;
    }
}
