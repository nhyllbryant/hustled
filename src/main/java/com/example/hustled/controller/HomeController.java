package com.example.hustled.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @GetMapping({"/", "/index"})
    public String home(@RequestParam(value = "showLogin", required = false) Boolean showLogin,
                       Model model) {
        model.addAttribute("showLogin", Boolean.TRUE.equals(showLogin));

        if (Boolean.TRUE.equals(showLogin)) {
            model.addAttribute("loginHeader", "Registration Successful");
        } else {
            model.addAttribute("loginHeader", "Login");
        }

        return "index";
    }


}
