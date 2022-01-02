package idv.lingerkptor.example.springboot.dao.Exception;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductIsExistException
 * Author:   lingerkptor
 * Date:     2021/6/17 下午 06:43
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/17 下午 06:43</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class ProductIsExistException extends RuntimeException {
    public ProductIsExistException(){
        super("產品已經存在");
    }
}
