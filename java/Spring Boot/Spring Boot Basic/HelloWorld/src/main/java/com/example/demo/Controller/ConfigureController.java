package com.example.demo.Controller;

import com.example.demo.config.bean.PersonInfo;
import com.example.demo.config.bean.testPersonList;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ConfigureController {
    private PersonInfo personInfo;
    private com.example.demo.config.bean.testPersonList testPersonList;
    private List<PersonInfo> personList;

    public ConfigureController(@Autowired PersonInfo personInfo,
                               @Autowired testPersonList list,
                               @Autowired @Qualifier("personList") List<PersonInfo> personList) {
        this.personInfo = personInfo;
        this.testPersonList = list;
        this.personList = personList;
    }


    @GetMapping("/getPersonInfo")
    @ResponseBody
    public String getPersonInfo() {
        return "{" +
                "  \"name\":" + personInfo.getName() +
                ",  \"age\":" + personInfo.getAge() +
                "}";
    }

    @GetMapping(value = "/testPersonList")
    @ResponseBody
    public String getTestPersonList() {
        Gson gson = new Gson();
        return gson.toJson(testPersonList);
    }

    @GetMapping(value = "/getPersonList")
    @ResponseBody
    public String getPersonList() {
        Gson gson = new Gson();
        return gson.toJson(personList);
    }
}
