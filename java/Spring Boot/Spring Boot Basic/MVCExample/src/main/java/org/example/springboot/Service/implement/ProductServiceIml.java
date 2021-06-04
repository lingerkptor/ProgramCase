package org.example.springboot.Service.implement;

import org.example.springboot.Service.Exception.ProductCountDeficiency;
import org.example.springboot.Service.Exception.ProductIsExist;
import org.example.springboot.Service.ProductService;
import org.example.springboot.Service.Exception.ProductNonExist;
import org.example.springboot.bean.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductService
 * Author:   lingerkptor
 * Date:     2021/5/30 下午 04:48
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/30 下午 04:48</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Service
public class ProductServiceIml implements ProductService {
    private static Map<String, Product> productMap = new HashMap<>();


    {
        productMap.put("001", new Product("001", "酒精", 60, 100));
        productMap.put("002", new Product("002", "口罩", 4, 5000000));
        productMap.put("003", new Product("003", "泡麵", 15, 10000000));
        productMap.put("004", new Product("004", "礦泉水", 10, 20000));
    }


    @Override
    public Product[] getProductList() {
        return productMap.values().toArray(new Product[0]);
    }

    @Override
    public Product getProduct(String id) throws ProductNonExist {
        Product product = productMap.get(id);
        if (product == null)
            throw new ProductNonExist(id);
        return product;
    }

    @Override
    public void addProduct(Product product) throws ProductIsExist {
        try {
            this.getProduct(product.getId());
        } catch (ProductNonExist productNonExist) {
            productMap.put(product.getId(), product);
        }
        throw new ProductIsExist(product.getId());
    }

    @Override
    public void selloutProduct(String id, int count) throws ProductCountDeficiency, ProductNonExist {
        Product product = this.getProduct(id);
        if (product.getCount() >= count) {
            product.setCount(product.getCount() - count);
        } else
            throw new ProductCountDeficiency(id);
    }

    @Override
    public void setProductCount(String id, int count) throws ProductNonExist {
        Product product = this.getProduct(id);
        product.setCount(count);
    }

    @Override
    public int getProductCount() {
        return productMap.size();
    }


}
