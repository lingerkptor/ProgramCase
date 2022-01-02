package idv.lingerkptor.example.springboot.dao.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductTableNotExistException
 * Author:   lingerkptor
 * Date:     2021/6/15 下午 09:55
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/15 下午 09:55</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductTableNotExistException extends RuntimeException {
    public ProductTableNotExistException(){
        super("Product Table Isn't Exist.");
    }
}
