package db.repository.impl;

import db.JdbcTemplate;
import db.mapper.ManufacturerMapper;
import db.mapper.Mapper;
import db.repository.Repository;
import db.specification.SQLSpecification;
import entity.Manufacturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLManufacturerRepository implements Repository<Manufacturer> {

    private static final String INSERT_NEW_MANUFACTURER = "insert into `manufacturer` values (default, ?)";
    private static final String DELETE_MANUFACTURER_BY_ID = "delete * from `manufacturer` where manufacturer.id=?";
    private static final String UPDATE_MANUFACTURER_BY_ID = "update `manufacturer` set manufacturer.name=? where manufacturer.id=?";

    private Mapper<Manufacturer> mapper;
    private JdbcTemplate<Manufacturer> jdbcTemplate;

    public SQLManufacturerRepository() {
        this.mapper = new ManufacturerMapper();
        jdbcTemplate = new JdbcTemplate<>(mapper);
    }

    @Override
    public void save(Connection connection, Manufacturer manufacturer) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_NEW_MANUFACTURER)) {
            pstmt.setString(1, manufacturer.getName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection connection, Manufacturer manufacturer) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_MANUFACTURER_BY_ID)) {
            pstmt.setInt(1, manufacturer.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Connection connection, Manufacturer manufacturer) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_MANUFACTURER_BY_ID)) {
            int k = 1;
            pstmt.setString(k++, manufacturer.getName());
            pstmt.setInt(k, manufacturer.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Manufacturer> query(Connection connection, SQLSpecification specification) throws SQLException {
        return jdbcTemplate.getAllBySpecification(connection, specification);
    }

    @Override
    public int getCount(Connection connection, SQLSpecification specification) throws SQLException {
        return jdbcTemplate.getCountBySpecification(connection, specification);
    }
}
