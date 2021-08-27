package org.example.springboot.Service.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductNonExist
 * Author:   lingerkptor
 * Date:     2021/6/2 下午 10:09
 * Description: 產品不存在 例外
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/2 下午 10:09</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductNonExist extends Exception{
    public ProductNonExist(String id){
        super("Id: "+id +" isn't Exist.");
    }
}
