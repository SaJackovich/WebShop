package db.mapper;

import container.MysqlProductFields;
import entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerMapper implements Mapper<Manufacturer> {

    @Override
    public Manufacturer extract(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt(MysqlProductFields.MANUFACTURER_ID));
        manufacturer.setName(resultSet.getString(MysqlProductFields.MANUFACTURER_NAME));
        return manufacturer;
    }
}
