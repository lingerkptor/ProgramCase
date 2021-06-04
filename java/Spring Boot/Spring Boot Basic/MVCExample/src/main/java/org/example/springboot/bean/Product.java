package org.example.springboot.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: Product
 * Author:   lingerkptor
 * Date:     2021/5/30 下午 03:26
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/30 下午 03:26</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class Product {
    @Getter
    private final String id;
    @Getter
    private final String productName;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    private int count;

    public Product(String id, String productName, int price, int count) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }

}
