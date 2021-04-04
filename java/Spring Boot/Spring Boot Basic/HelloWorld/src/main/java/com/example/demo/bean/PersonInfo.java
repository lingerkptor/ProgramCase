package com.example.demo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright ©, 2020-$(YEAR), lingerkptor
 * FileName: PersonInfo
 * Author:   lingerkptor
 * Date:     2021/3/21 下午 01:13
 * Description: Properties(yml) "personinfo" attribute Bean
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/3/21 下午 01:13 0 create
 */
//@Component
//@ConfigurationProperties(prefix = "personinfo")
@Data //@Getter @Setter
public class PersonInfo {
    private String name;
    private int age;

}
