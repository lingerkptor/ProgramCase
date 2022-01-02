package idv.lingerkptor.example.springboot.bean;

import lombok.Data;

import javax.validation.constraints.Min;


/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: Product
 * Author:   lingerkptor
 * Date:     2021/5/30 下午 03:26
 * Description: Product bean
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/30 下午 03:26</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Data
public class Product {
    private Integer serial = 0;

    private String productName = null;

    @Min(value = 0)
    private int price;

    @Min(value = 0)
    private int count;

    @Override
    public String toString() {
//        StringBuilder builder = new StringBuilder("Product Serial: ");
//        builder.append(serial);
//        builder.append("\n Product Name: ");
//        builder.append(  productName);
//        builder.append("\n Product Price: ");
//        builder.append(  price);
//        builder.append("\n Product Count: ");
//        builder.append( count);
//        return builder.toString();
        return String.format("Product Serial: %d \n Product Name: %s \n Product Price: %d \n Product Count: %d"
                , serial, productName, price, count);
    }

    @Override
    public boolean equals(Object product) {
        if (this.getClass() != product.getClass())
            return false;
        return this.serial.equals(((Product) product).getSerial());
    }


}
