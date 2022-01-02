package org.example.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: Application
 * Author:   lingerkptor
 * Date:     2021/10/31 下午 01:51
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/10/31 下午 01:51</td><td>1</td><td>file created</td></tr>
 * </table>
 */


@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}