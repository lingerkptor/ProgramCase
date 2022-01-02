package idv.lingerkptor.example.springboot.dao;

import idv.lingerkptor.example.springboot.bean.Product;
import idv.lingerkptor.example.springboot.dao.BeanMapper.ProductBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductDao
 * Author:   lingerkptor
 * Date:     2021/6/13 下午 02:47
 * Description: Product Data Access Object
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/13 下午 02:47</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Component
public class ProductDao {
    public ProductDao(@Autowired JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private final JdbcTemplate jdbcTemplate;

    public void createTable() {
        String sql = "create table product (serial int not null AUTO_INCREMENT primary key " +
                ",productName varChar(20) not null " +
                ", price int " +
                ", count int ) ;";
        jdbcTemplate.execute(sql);
    }

    public void insertProduct(Product product) {
        String sql = "INSERT INTO product (productName,price,count) value (?,?,?);";
        jdbcTemplate.update(sql, product.getProductName(), product.getPrice(), product.getCount());
    }

    public void modifyProduct(Product product) {
        String sql = "UPDATE product SET productName = ?, price = ?, count = ? \n" +
                "WHERE serial = ? ;  ";
        jdbcTemplate.update(sql, product.getProductName(), product.getPrice(), product.getCount(), product.getSerial());
    }

    public void dropTable() {
        String sql = "drop table if exists product;";
        jdbcTemplate.execute(sql);
    }

    public Product selectProduct(int serial) {
        String sql = "SELECT *\n" +
                "FROM product\n" +
                "WHERE serial = ? ;";

        List<Product> products = jdbcTemplate.query(sql, new ProductBeanMapper(), serial);
        return products.get(0);
    }

    public void deleteProduct(int serial) {
        String sql = "DELETE\n" +
                "FROM product\n" +
                "WHERE serial = ?;";
        jdbcTemplate.update(sql, serial);
    }

    public List<Product> selectAllProductList() {
        String sql = "SELECT * FROM product ;";
        return jdbcTemplate.query(sql, new ProductBeanMapper());
    }
}
