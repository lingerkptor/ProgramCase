package idv.lingerkptor.example.springboot.dao;

import idv.lingerkptor.example.springboot.APP;
import idv.lingerkptor.example.springboot.bean.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Valid;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductTest
 * Author:   lingerkptor
 * Date:     2021/6/13 上午 11:32
 * Description: ProductDao.class Test
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/13 上午 11:32</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@SpringBootTest(classes = APP.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDaoTest {
    @Autowired
    private ProductDao dao;

    @Test
    @Order(1)
    public void testCreateProductTable() {
        dao.createTable();
    }

    @Valid
    private static Product product = new Product();


    static {
        product.setSerial(null);
        product.setProductName("abc");
        product.setPrice(10);
        product.setCount(100);
    }

    @Test
    @Order(2)
    public void testInsertProduct() {
        dao.insertProduct(product);
    }

    @Test
    @Order(3)
    public void testSelectProduct() {
        product = dao.selectProduct(1);
        System.out.println(product.toString());
    }

    @Test
    @Order(4)
    public void testUpdateProduct() {
        product.setCount(1000);
        dao.modifyProduct(product);
    }

    @Test
    @Order(5)
    public void testSelectProduct2() {
        product = dao.selectProduct(1);
        System.out.println(product.toString());
    }

    @Test
    @Order(6)
    public void testDeleteProduct() {
        dao.deleteProduct(product.getSerial());
    }

    @Test
    @Order(7)
    public void testSelectProduct3() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            product = dao.selectProduct(1);
            System.out.println(product.toString());
        });
    }

    @Test
    @Order(8)
    public void testInsertProduct1() {
        dao.insertProduct(product);
    }

    @Test
    @Order(9)
    public void testSelectProduct4() {
        List<Product> productList = dao.selectAllProductList();
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @Order(100)
    public void testDropProductTable() {
        dao.dropTable();
    }
}
