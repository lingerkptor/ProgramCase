package org.example.springboot.Service.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductCountDeficiency
 * Author:   lingerkptor
 * Date:     2021/6/2 下午 10:24
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/2 下午 10:24</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductCountDeficiency extends Exception{
    public ProductCountDeficiency(String id){
        super("id: "+ id +" Count Deficiency.");
    }
}
