package com.example.demo.Controller;

import com.example.demo.bean.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright ©, 2020-$(YEAR), lingerkptor
 * FileName: HelloWorldController
 * Author:   lingerkptor
 * Date:     2021/3/13 下午 06:27
 * Description: Hello World Controller
 * History:
 * <author> <time>  <version> <desc>
 * lingerkptor  2021/3/13    0  Create Controller
 */
//@RestController //@Controller + @ResponseBody
@Controller
public class HelloWorldController {

    private PersonInfo personInfo;

    public HelloWorldController(@Autowired PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "{\n" +
                "  \"test\": \"aaa\",\n" +
                "  \"abc\": 123\n" +
                "}";
    }

    @RequestMapping("/helloworld")
    public String helloWorld(Model model) throws Exception {
        model.addAttribute("mav", "HelloWorldController ,Spring Boot!");
        //檢視(view)的位置和名稱，檢視位於example資料夾下，檢視檔案為hello.html。
        return "example/hello";
    }



    @GetMapping("/getPersonInfo")
    @ResponseBody
    public String getPersonInfo() {
        return "{" +
                "  \"name\":" + personInfo.getName() +
                ",  \"age\":" + personInfo.getAge() +
                "}";
    }

}
