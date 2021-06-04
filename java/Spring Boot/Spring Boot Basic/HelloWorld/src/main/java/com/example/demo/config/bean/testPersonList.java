package com.example.demo.config.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright ©, 2020-$(YEAR), lingerkptor
 * FileName: testPersonList
 * Author:   lingerkptor
 * Date:     2021/3/21 下午 03:09
 * Description:
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/3/21 下午 03:09 0 create
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "test")
public class testPersonList {
    private String objname;
    private String prop1;
    private List<PersonInfo> personlist = new ArrayList<PersonInfo>();
}
