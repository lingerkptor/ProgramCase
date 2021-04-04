package com.example.demo;

import com.example.demo.bean.PersonInfo;
import com.example.demo.bean.testPersonList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Copyright ©, 2020-$(YEAR), lingerkptor
 * FileName: PropertTest
 * Author:   lingerkptor
 * Date:     2021/3/17 下午 05:56
 * Description: test application.yml content
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/3/17 下午 05:56 0 create
 */

@SpringBootTest
public class PropertiesTest {
    @Value("${age}")
    private int age;

    @Value("${name}")
    private String name;

    @Test
    public void nameAttributeTest() {
        Assertions.assertEquals(name, "zhaodabao");
    }

    @Test
    public void ageAttributeTest() {
        Assertions.assertEquals(19, age);
    }

    @Autowired
    private PersonInfo personInfo;

    @Test
    public void personInfoTest() {
        Assertions.assertEquals(3, personInfo.getAge());
        Assertions.assertEquals("zhaoxiaobao", personInfo.getName());
    }

//    @Autowired
//    @Qualifier("personList")//這行註解去掉會有很有趣的事情
    @Resource(name = "personList")
    private List<PersonInfo> personInfoList;

    @Test
    public void personInfoListTest() {
        System.out.println(personInfoList.size());
        personInfoList.forEach(personInfo1 -> System.out.println(personInfo1.getName() + personInfo1.getAge()));
    }

    @Autowired
    private testPersonList testPersonListAttr;

    @Test
    public void personListTest() {
        testPersonListAttr.getPersonlist().forEach(personInfo1 -> System.out.println(personInfo1.getName() + personInfo1.getAge()));

    }

}
