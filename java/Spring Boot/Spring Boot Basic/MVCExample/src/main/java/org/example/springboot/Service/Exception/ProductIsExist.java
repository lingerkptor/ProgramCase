package org.example.springboot.Service.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductIsExist
 * Author:   lingerkptor
 * Date:     2021/6/2 下午 10:15
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/2 下午 10:15</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductIsExist extends Exception {
    public ProductIsExist(String id) {
        super("id: " + id + " is Exist.");
    }
}
