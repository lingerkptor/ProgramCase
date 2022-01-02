package idv.lingerkptor.example.springboot.dao.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductTableExistException
 * Author:   lingerkptor
 * Date:     2021/6/15 下午 08:24
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/15 下午 08:24</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductTableExistException extends RuntimeException {
    public ProductTableExistException() {
        super("Product Table Is Exist.");
    }

}
