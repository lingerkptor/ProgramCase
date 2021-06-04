package org.example.springboot.Controller;

import org.example.springboot.bean.HelloWorldModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: HelloWorldControler
 * Author:   lingerkptor
 * Date:     2021/4/17 下午 08:57
 * Description: thymeleaf Example Controller Case 1
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/17 下午 08:57 0 created
 */
@Controller
public class HelloWorldController {
    @RequestMapping(value = "/hello/{user}", method = RequestMethod.GET)
    public String helloWorld(Model model // Model 在這裡
                            , @PathVariable String user) throws Exception {
        model.addAttribute("title", "Hello World");
        model.addAttribute("user", user);
        return "HelloWorld";
    }

}
