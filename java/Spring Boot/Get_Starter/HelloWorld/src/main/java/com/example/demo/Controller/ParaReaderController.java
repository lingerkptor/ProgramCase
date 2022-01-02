package com.example.demo.Controller;

import com.example.demo.config.bean.ParaObjectBean;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ParaReader")
public class ParaReaderController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String nullParaVariable() {
        return "";
    }

    @RequestMapping(value = "/{para1}", method = RequestMethod.GET)
    @ResponseBody
    public String getPathVariable(@PathVariable("para1") String para1) {
        return "PathVariable =  " + para1;
    }

    @RequestMapping(value = "/{postPara}", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String getPostVariable(@PathVariable("postPara") String postPara, @RequestBody ParaObjectBean bean) {
        Gson gson = new Gson();
        System.out.println("postPara = " + postPara);
        return gson.toJson(bean);
    }

    @RequestMapping(value = "/formData" ,method = RequestMethod.POST, consumes="multipart/form-data")
    @ResponseBody
    public String getFormData(@RequestParam("name") String name ,@RequestParam("number") int number){
        ParaObjectBean bean = new ParaObjectBean();
        bean.setName(name);
        bean.setNumber(number);
        Gson gson = new Gson();
        return gson.toJson(bean);
    }


}
