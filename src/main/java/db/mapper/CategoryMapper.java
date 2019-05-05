package db.mapper;

import container.MysqlProductFields;
import entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements Mapper<Category> {

    @Override
    public Category extract(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(MysqlProductFields.CATEGORY_ID));
        category.setName(resultSet.getString(MysqlProductFields.CATEGORY_NAME));
        return category;
    }
}