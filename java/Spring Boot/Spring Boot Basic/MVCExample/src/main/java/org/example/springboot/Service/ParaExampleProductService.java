package org.example.springboot.Service;

import org.example.springboot.bean.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ParaExampleProductService
 * Author:   lingerkptor
 * Date:     2021/5/30 下午 03:24
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/30 下午 03:24</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Service
public class ParaExampleProductService {
    private static Map<String, Product> productMap = new HashMap<>();

    {
        productMap.put("001", new Product("001", "酒精", 60, 100));
        productMap.put("002", new Product("002", "口罩", 4, 5000000));
        productMap.put("003", new Product("003", "泡麵", 15, 10000000));
        productMap.put("004", new Product("004", "礦泉水", 10, 20000));
    }

    public Product getProduct(String id) {
        System.out.println(productMap.get(id));
        return productMap.get(id);
    }

    public Object[] addProduct(Product product) {
        productMap.put(product.getId(), product);
        return productMap.values().toArray(new Product[0]);
    }
}
