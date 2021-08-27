package org.example.springboot.Service;

import org.example.springboot.Service.Exception.ProductCountDeficiency;
import org.example.springboot.Service.Exception.ProductIsExist;
import org.example.springboot.Service.Exception.ProductNonExist;
import org.example.springboot.bean.Product;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductService
 * Author:   lingerkptor
 * Date:     2021/5/30 下午 04:52
 * Description: 產品相關服務
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/30 下午 04:52</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public interface ProductService {


    /**
     * 取得商品清單
     *
     * @return 產品清單
     */
    public Product[] getProductList();

    /**
     * 取得商品
     *
     * @param id 產品序號
     * @return 產品
     */
    public Product getProduct(String id) throws ProductNonExist;

    /**
     * 新增商品
     *
     * @param product 產品物件
     * @return 建立是否成功
     */
    public void addProduct(Product product) throws ProductIsExist;

    /**
     * 售出商品
     *
     * @param id    產品序號
     * @param count 銷售數量
     * @return
     */
    public void selloutProduct(String id, int count) throws ProductCountDeficiency,ProductNonExist;

    /**
     * 增加產品數量
     *
     * @param id        產品序號
     * @param count 增加的數量
     * @return 產品
     */
    public void setProductCount(String id, int count)throws ProductNonExist;

    /**
     * 取得產品種類數量
     *
     * @return 產品種類數量
     */
    public int getProductCount();

}
