package org.example.springboot.bean;

import lombok.Data;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: bean
 * Author:   lingerkptor
 * Date:     2021/5/29 下午 05:24
 * Description: 建立基本的資料
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/29 下午 05:24</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Data // @Getter @Setter
public class HelloWorldModel {
    private String title ="HelloWorld";
    private String body = "";
}
