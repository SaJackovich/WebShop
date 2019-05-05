package db.repository.impl;

import db.JdbcTemplate;
import db.mapper.CategoryMapper;
import db.mapper.Mapper;
import db.repository.Repository;
import db.specification.SQLSpecification;
import entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQlCategoryRepository implements Repository<Category> {

    private static final String INSERT_NEW_CATEGORY = "insert into `category` values (default, ?)";
    private static final String DELETE_CATEGORY_BY_ID = "delete * from `category` where category.id=?";
    private static final String UPDATE_CATEGORY_BY_ID = "update category set category.name=? where category.id=?";

    private Mapper<Category> mapper;
    private JdbcTemplate<Category> jdbcTemplate;

    public SQlCategoryRepository() {
        this.mapper = new CategoryMapper();
        jdbcTemplate = new JdbcTemplate<>(mapper);
    }

    @Override
    public void save(Connection connection, Category category) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_NEW_CATEGORY)) {
            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection connection, Category category) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_CATEGORY_BY_ID)) {
            pstmt.setInt(1, category.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Connection connection, Category category) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_CATEGORY_BY_ID)) {
            int k = 1;
            pstmt.setString(k++, category.getName());
            pstmt.setInt(k, category.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Category> query(Connection connection, SQLSpecification specification) throws SQLException {
        return jdbcTemplate.getAllBySpecification(connection, specification);
    }

    @Override
    public int getCount(Connection connection, SQLSpecification specification) throws SQLException {
        return jdbcTemplate.getCountBySpecification(connection, specification);
    }
}
