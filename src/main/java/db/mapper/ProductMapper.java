package db.mapper;

import container.MysqlProductFields;
import entity.Category;
import entity.Manufacturer;
import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements Mapper<Product> {

    private Mapper<Category> categoryMapper;
    private Mapper<Manufacturer> manufacturerMapper;

    public ProductMapper() {
        this.categoryMapper = new CategoryMapper();
        this.manufacturerMapper = new ManufacturerMapper();
    }

    @Override
    public Product extract(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(MysqlProductFields.PRODUCT_ID));
        product.setName(resultSet.getString(MysqlProductFields.PRODUCT_NAME));
        product.setPrice(resultSet.getBigDecimal(MysqlProductFields.PRODUCT_PRICE));
        product.setImage(resultSet.getString(MysqlProductFields.PRODUCT_IMAGE));
        product.setDescription(resultSet.getString(MysqlProductFields.PRODUCT_DESCRIPTION));
        product.setQuantity(resultSet.getInt(MysqlProductFields.PRODUCT_QUANTITY));
        return extractProductObjects(product, resultSet);
    }

    private Product extractProductObjects(Product product, ResultSet resultSet) throws SQLException {
        Category category = categoryMapper.extract(resultSet);
        product.setCategory(category);
        Manufacturer manufacturer = manufacturerMapper.extract(resultSet);
        product.setManufacturer(manufacturer);
        return product;
    }
}
