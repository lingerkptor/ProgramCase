package org.example.springboot.Controller;


import org.example.springboot.Service.ParaExampleProductService;
import org.example.springboot.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ParaExampleController
 * Author:   lingerkptor
 * Date:     2021/4/17 下午 10:21
 * Description: 參數讀取的使用範例
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/17 下午 10:21 0 create
 */

@RestController
@RequestMapping("paraExample/product")
public class ParaExampleController {

    private ParaExampleProductService paraExampleProductService;

    public ParaExampleController(@Autowired ParaExampleProductService paraExampleProductService) {
        this.paraExampleProductService = paraExampleProductService;
    }


    /**
     * Path Parameter 路徑參數範例
     * 取得產品
     *
     * @param model Model
     * @param id    產品ID
     * @return 導向product/show
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProduct(Model model, @PathVariable("id") String id) {
        model.addAttribute("product", paraExampleProductService.getProduct(id));
        return "product/show";
    }

    /**
     * 取得路徑中的變數(/product?id=XXX)
     * 取得產品
     *
     * @param model Model
     * @param id    產品ID
     * @return 導向product/show
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProduct(@RequestParam String id, Model model) {
        model.addAttribute("product", paraExampleProductService.getProduct(id));
        return "product/show";
    }

    /**
     * 取得Form中的參數(/product?id=XXX)
     * 取得產品
     *
     * @param id 產品ID
     * @return 導向product/show
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView getProduct(String id) {
        ModelAndView modelAndView = new ModelAndView("product/show");
        modelAndView.addObject("product", paraExampleProductService.getProduct(id));
        return modelAndView;
    }

    /**
     * Json 物件做為參數(已轉換成物件)
     * 新增商品
     *
     * @param product 產品
     * @return 產品清單 之後會透過@ResponseBody轉成JSON字串
     */
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Object[] addProduct(@RequestBody Product product) {
        return paraExampleProductService.addProduct(product);
    }

}
