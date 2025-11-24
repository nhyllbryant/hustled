package com.example.hustled.controller;

import com.example.hustled.entity.User;
import com.example.hustled.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // ✅ New endpoint: serve login modal fragment dynamically
    @GetMapping("/modal/login")
    public String getLoginModal(Model model,
                                @RequestParam(value = "fromRegister", required = false) Boolean fromRegister) {

        if (Boolean.TRUE.equals(fromRegister)) {
            model.addAttribute("loginHeader", "Registration Successful");
        } else {
            model.addAttribute("loginHeader", "Login");
        }

        return "fragments/login-modal :: loginModal";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // ✅ New endpoint: serve register modal fragment dynamically
    @GetMapping("/modal/register")
    public String getRegisterModal(Model model) {
        model.addAttribute("user", new User());
        return "fragments/register-modal :: registerModal";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") User user,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        boolean ok = userService.register(user);
        if (!ok) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }
        // ✅ Redirect to index with flag
        return "redirect:/index?showLogin=true";
    }
}
