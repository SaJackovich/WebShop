package db.repository.impl;

import db.JdbcTemplate;
import db.mapper.Mapper;
import db.mapper.ProductMapper;
import db.repository.Repository;
import db.specification.SQLSpecification;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLProductRepository implements Repository<Product> {

    private Mapper<Product> productMapper;
    private JdbcTemplate<Product> productTemplate;

    private static final String INSERT_NEW_PRODUCT = "insert into `product` values (default, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_PRODUCT = "delete * from `product` where id=?";
    private static final String UPDATE_PRODUCT = "update `product` set name=?, description=?, price=?, quantity=?, id_manufacturer=?, id_category=? where id=?";

    public SQLProductRepository() {
        productMapper = new ProductMapper();
        productTemplate = new JdbcTemplate<>(productMapper);
    }

    @Override
    public void save(Connection connection, Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PRODUCT)) {
            int k = 1;
            statement.setString(k++, product.getName());
            statement.setString(k++, product.getDescription());
            statement.setBigDecimal(k++, product.getPrice());
            statement.setInt(k++, product.getQuantity());
            statement.setInt(k++, product.getManufacturer().getId());
            statement.setInt(k, product.getCategory().getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Connection connection, Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Connection connection, Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            int k = 1;
            statement.setString(k++, product.getName());
            statement.setString(k++, product.getDescription());
            statement.setBigDecimal(k++, product.getPrice());
            statement.setInt(k++, product.getQuantity());
            statement.setInt(k++, product.getManufacturer().getId());
            statement.setInt(k++, product.getCategory().getId());
            statement.setInt(k, product.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Product> query(Connection connection, SQLSpecification specification) throws SQLException {
        return productTemplate.getAllBySpecification(connection, specification);
    }

    @Override
    public int getCount(Connection connection, SQLSpecification specification) throws SQLException {
        return productTemplate.getCountBySpecification(connection, specification);
    }
}
