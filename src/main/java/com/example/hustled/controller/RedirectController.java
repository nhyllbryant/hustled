package com.example.hustled.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/route-by-role")
    public String routeByRole(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        switch (role) {
            case "CANDIDATE": return "redirect:/candidate-profile";
            case "EMPLOYER": return "redirect:/employer-profile";
            case "ADMIN": return "redirect:/admin-profile";
            default: return "redirect:/index";
        }
    }
}
