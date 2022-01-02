package idv.lingerkptor.example.springboot.dao;

import idv.lingerkptor.example.springboot.APP;
import idv.lingerkptor.example.springboot.bean.Product;
import idv.lingerkptor.example.springboot.dao.Exception.ProductIsExistException;
import idv.lingerkptor.example.springboot.dao.Exception.ProductTableExistException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.validation.Valid;


/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductTransactionDaoTest
 * Author:   lingerkptor
 * Date:     2021/6/15 下午 07:15
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/15 下午 07:15</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@SpringBootTest(classes = APP.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTransactionDaoTest {

    @Autowired
    private ProductTransactionDao dao;

    @Valid
    private static Product product = new Product();


    static {
        product.setSerial(null);
        product.setProductName("abc");
        product.setPrice(10);
        product.setCount(100);
    }

    /**
     * 建立資料庫
     */
    @Test
    @Order(1)
    public void testCreateProductTable() {
        Assert.isTrue(dao.createProductTable(), "建立資料庫失敗");
    }

    /**
     * 再次建立資料庫,預期會失敗跳出例外
     */
    @Test
    @Order(2)
    public void testCreateProductTable2() {
        Assertions.assertThrows(ProductTableExistException.class
                , () -> dao.createProductTable()
                , "預期會跳出ProductTableExistException 例外");
    }

    @Test
    @Order(3)
    public void testAppendProduct() {
        Assert.isTrue(dao.appendProduct(product), "預期新增產品成功");
    }

    @Test
    @Order(4)
    public void testAppendProduct2() {
        Assertions.assertThrows(ProductIsExistException.class,
                () -> dao.appendProduct(product), "預期跳出例外產品已經新增");
    }

    @Test
    @Order(5)
    public void testQueryProduct() {
        Product newProduct =  dao.queryProductByName(product);
        Assertions.assertEquals(product.getProductName(), newProduct.getProductName(), "預期會相等");
        Assertions.assertEquals(product.getPrice(), newProduct.getPrice(), "預期會相等");
        Assertions.assertEquals(product.getCount(), newProduct.getCount(), "預期會相等");
        Assertions.assertNotEquals(product.getSerial(), newProduct.getSerial(), "預期會不相等");
        product = dao.queryProductByName(product);
    }

    @Test
    @Order(6)
    public void testModifyProduct() {
        product.setCount(10000);
        Assertions.assertEquals(true, dao.modifyProduct(product), "預期修改庫存為10000");
    }

    @Test
    @Order(7)
    public void testDeleteProduct(){
        Assertions.assertEquals(true,dao.deleteProduct(product),"");
    }


    @Test
    @Order(99)
    public void testDropProductTable() {
        Assert.isTrue(dao.dropProductTable(), "預期刪除資料庫成功");
    }
}
