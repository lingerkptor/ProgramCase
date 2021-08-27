package org.example.springboot.Controller;

import org.example.springboot.bean.UserForm;
import org.springframework.boot.Banner;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: UserRegisterController
 * Author:   lingerkptor
 * Date:     2021/6/5 下午 05:06
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/5 下午 05:06</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Controller
public class UserRegisterController {

    @GetMapping("/user/form")
//    public String showForm(@ModelAttribute(binding = false) UserForm user) {
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "UserRegister";
    }

    /**
     * 使用者註冊,
     * @param userForm   必須與類別名相同(第一個字母要小寫)，不然會出錯
     * @param bindResult
     * @param attributes
     * @param model
     * @return
     */
    @PostMapping(value = "/user/register")
    public String registerUser(@Valid @ModelAttribute UserForm userForm, BindingResult bindResult, RedirectAttributes attributes, Model model) {

        if (bindResult.hasErrors()) {
//            for (Map.Entry<String, Object> entrySet :
//                    bindResult.getModel().entrySet()) {
//                System.out.println("key: " + entrySet.getKey());
//                System.out.println("value: " + entrySet.getValue());
//            }
//            System.out.println("error println result.");
            return "UserRegister";
        }
//        attributes.addFlashAttribute("userForm", userForm);

//       return "redirect:/user/User";
        return "User";
    }

    @PostMapping("/user/User")
    public String successRegister(Model model, @RequestParam UserForm userForm) {
        model.addAttribute("userForm", userForm);
        return "User";
    }


}
