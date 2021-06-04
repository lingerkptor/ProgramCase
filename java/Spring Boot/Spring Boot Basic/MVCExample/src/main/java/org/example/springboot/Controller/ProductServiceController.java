package org.example.springboot.Controller;

import org.example.springboot.Service.Exception.ProductCountDeficiency;
import org.example.springboot.Service.Exception.ProductIsExist;
import org.example.springboot.Service.Exception.ProductNonExist;
import org.example.springboot.Service.ProductService;
import org.example.springboot.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ProductServiceControler
 * Author:   lingerkptor
 * Date:     2021/5/30 下午 05:08
 * Description: MVC Controller 統一範例
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/5/30 下午 05:08</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Controller
@RequestMapping("/product")
public class ProductServiceController {

    private ProductService productService;

    public ProductServiceController(@Autowired ProductService service) {
        this.productService = service;
    }

    private ModelAndView listPageModelViewFactory() {
        return new ModelAndView("product/list");
    }

    private ModelAndView errorPageModelViewFactory() {
        return new ModelAndView("product/error");
    }

    private ModelAndView showPageModelViewFactory() {
        return new ModelAndView("product/show");
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getProductList() {
        ModelAndView modelAndView = this.listPageModelViewFactory();
        modelAndView.addObject("productList", productService.getProductList());
        modelAndView.addObject("productCount",productService.getProductCount());
        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ModelAndView getProduct(@PathVariable String id) {
        try {
            return this.showPageModelViewFactory().addObject("product", productService.getProduct(id));
        } catch (ProductNonExist productNonExist) {
//            productNonExist.printStackTrace();
            return this.errorPageModelViewFactory().addObject("message", productNonExist.getMessage());
        }

    }

    @RequestMapping(value = {"/new"}, method = {RequestMethod.POST})
    public ModelAndView addProduct(String id, String productName, int price, int count) {
        Product product = new Product(id, productName, price, count);
        return this.addProduct(product);
    }

    //    @RequestMapping(value = {"/new"},method = {RequestMethod.POST})
    public ModelAndView addProduct(Product product) {
        try {
            productService.addProduct(product);
            return this.getProductList();
        } catch (ProductIsExist productIsExist) {
//            productIsExist.printStackTrace();
            return this.errorPageModelViewFactory().addObject("message", productIsExist.getMessage());
        }

    }

    @RequestMapping(value = {"/sellout"}, method = {RequestMethod.POST})
    public ModelAndView selloutProduct(String id, int count) {
        try {
            productService.selloutProduct(id, count);
        } catch (ProductCountDeficiency productCountDeficiency) {
//            productCountDeficiency.printStackTrace();
            return this.errorPageModelViewFactory().addObject("message", productCountDeficiency.getMessage());
        } catch (ProductNonExist productNonExist) {
//            productNonExist.printStackTrace();
            return this.errorPageModelViewFactory().addObject("message", productNonExist.getMessage());
        }
        return this.getProduct(id);
    }

    @RequestMapping(value = {"/setProductCount"}, method = {RequestMethod.POST})
    public ModelAndView setProductCount(String id, int addNumber) {
        try {
            productService.setProductCount(id, addNumber);
        } catch (ProductNonExist productNonExist) {
            productNonExist.printStackTrace();
            return this.errorPageModelViewFactory().addObject("message", productNonExist.getMessage());
        }

        return this.getProduct(id);
    }

    @RequestMapping(value = {"/productCount"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int getProductCount() {
        return productService.getProductCount();
    }
}
