package com.example.demo;

import com.example.demo.bean.PersonInfo;
import com.example.demo.bean.testPersonList;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright ©, 2020-$(YEAR), lingerkptor
 * FileName: PersonInfoConfig
 * Author:   lingerkptor
 * Date:     2021/3/21 下午 01:30
 * Description: PersonInfo Configuration
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/3/21 下午 01:30 0 create
 */
@Configuration
public class PersonInfoConfig {

    @Bean
    @ConfigurationProperties(prefix = "personinfo")
    public PersonInfo personInfoFactory() {
        return new PersonInfo();
    }

    @Bean
    @ConfigurationProperties(prefix = "test")//將yml內的test內的屬性填入新物件
    public testPersonList testPersonInfoListFactory() {
        return new testPersonList();
    }

    @Bean(name = "personList")
    @ConfigurationProperties(prefix = "personlist")
    public List<PersonInfo> personInfoListFactory() {
        return new ArrayList<PersonInfo>();
    }
}
