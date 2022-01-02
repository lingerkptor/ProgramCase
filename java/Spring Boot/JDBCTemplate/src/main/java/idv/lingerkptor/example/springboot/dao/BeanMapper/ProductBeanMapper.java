package idv.lingerkptor.example.springboot.dao.BeanMapper;

import idv.lingerkptor.example.springboot.bean.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: BeanMapper
 * Author:   lingerkptor
 * Date:     2021/6/14 下午 07:37
 * Description: ProductRowMapping
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/14 下午 07:37</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductBeanMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setSerial(resultSet.getInt("serial"));
        product.setProductName(resultSet.getString("productName"));
        product.setPrice(resultSet.getInt("price"));
        product.setCount(resultSet.getInt("count"));
        return product;

    }
}
