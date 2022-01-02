package idv.lingerkptor.example.springboot.dao;

import idv.lingerkptor.example.springboot.bean.Product;
import idv.lingerkptor.example.springboot.dao.BeanMapper.ProductBeanMapper;
import idv.lingerkptor.example.springboot.dao.Exception.ProductIsExistException;
import idv.lingerkptor.example.springboot.dao.Exception.ProductIsNotExistException;
import idv.lingerkptor.example.springboot.dao.Exception.ProductTableExistException;
import idv.lingerkptor.example.springboot.dao.Exception.ProductTableNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductTransactionDao
 * Author:   lingerkptor
 * Date:     2021/6/15 下午 07:11
 * Description: 現實上會出現產品資料存取
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/15 下午 07:11</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Component
public class ProductTransactionDao {
    public ProductTransactionDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "product";

    @Transactional
    public boolean createProductTable() throws ProductTableExistException {
        jdbcTemplate.query("SHOW TABLES LIKE ? ;", (rs) -> {
            if (rs.getString(1).equals(this.TABLE_NAME))
                throw new ProductTableExistException();
        }, this.TABLE_NAME);
        jdbcTemplate.execute(String.format("create table %s (", this.TABLE_NAME) +
                "    serial      int         not null AUTO_INCREMENT primary key ,\n" +
                "    productName varChar(20) not null ,\n" +
                "    price       int ,\n" +
                "    count       int\n);");
        boolean[] resultBoolean = new boolean[1];
        jdbcTemplate.query("SHOW TABLES LIKE ? ;", (rs) -> {
            resultBoolean[0] = rs.getString(1).equals(this.TABLE_NAME);
        }, this.TABLE_NAME);
        return resultBoolean[0];
    }


    @Transactional
    public boolean appendProduct(Product product) throws ProductTableExistException {
        boolean[] result = {true};
        // 檢測是否已有重複的名稱
        jdbcTemplate.query("SELECT COUNT(*) FROM product WHERE productName = ? ;", (rs) -> {
            if (rs.getInt(1) > 0)
                throw new ProductIsExistException();
        }, product.getProductName());
        // 加入產品
        jdbcTemplate.update("INSERT INTO product (productName,price,count) value (?,?,?);"
                , product.getProductName(), product.getPrice(), product.getCount());
        // 檢查是否有加入成功
        jdbcTemplate.query("SELECT COUNT(*) FROM product WHERE productName = ? ;", (rs) -> {
            if (rs.getInt(1) == 0)
                result[0] = false;
        }, product.getProductName());
        return result[0];
    }

    @Transactional
    public Product queryProductByName(Product product) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE productName = ? ;"
                , new ProductBeanMapper(), product.getProductName());

        return products.get(0);
    }

    @Transactional
    public boolean dropProductTable() throws ProductTableNotExistException {
        boolean[] result = {true};
        jdbcTemplate.query("SHOW TABLES LIKE ? ;", (rs) -> {
            if (!rs.getString(1).equals(this.TABLE_NAME))
                throw new ProductTableNotExistException();

        }, this.TABLE_NAME);
        jdbcTemplate.execute(String.format("drop table %s ;", this.TABLE_NAME));
        jdbcTemplate.query("SHOW TABLES LIKE ? ;", (rs) -> {
            result[0] = false;
        }, this.TABLE_NAME);
        return result[0];
    }

    @Transactional
    public boolean modifyProduct(Product product) {
        boolean[] result = {false};
        jdbcTemplate.query("SELECT COUNT(*) FROM product WHERE serial = ? ;", (rs) -> {
            if (rs.getInt(1) == 0)
                throw new ProductIsNotExistException();
        }, product.getSerial());
        jdbcTemplate.update("UPDATE product\n" +
                        "SET price = ? , count = ? \n" +
                        "WHERE serial = ?; ",
                product.getPrice(), product.getCount(), product.getSerial());
        jdbcTemplate.query("SELECT price,count FROM product WHERE serial = ? ;", (rs) -> {
            if (rs.getInt(1) == product.getPrice()
                    && rs.getInt(2) == product.getCount())
                result[0] = true;

        }, product.getSerial());
        return result[0];
    }

    @Transactional
    public boolean deleteProduct(Product product) throws ProductIsNotExistException{
        boolean[] result = {false};
        jdbcTemplate.query("SELECT COUNT(*) FROM product WHERE serial = ? ;", (rs) -> {
            if (rs.getInt(1) == 0)
                throw new ProductIsNotExistException();
        }, product.getSerial());
        jdbcTemplate.update("DELETE\n" +
                        "FROM " +
                        "product\n" +
                        "WHERE serial = ?; ",
                product.getPrice(), product.getCount(), product.getSerial());
        // TODO 尚未完成
        return result[1];
    }
}
