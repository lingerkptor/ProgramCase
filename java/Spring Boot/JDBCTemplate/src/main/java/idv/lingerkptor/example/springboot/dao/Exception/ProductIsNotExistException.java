package idv.lingerkptor.example.springboot.dao.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductIsNotExistException
 * Author:   lingerkptor
 * Date:     2021/6/17 下午 08:22
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/17 下午 08:22</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductIsNotExistException extends RuntimeException {
    public ProductIsNotExistException() {
        super("產品不存在");
    }
}
