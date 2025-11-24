package com.example.hustled.controller;

import com.example.hustled.entity.User;
import com.example.hustled.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", "/index"})
    public String home(@RequestParam(value = "showLogin", required = false) Boolean showLogin,
                       Model model,
                       Authentication authentication) {
        model.addAttribute("showLogin", Boolean.TRUE.equals(showLogin));

        if (Boolean.TRUE.equals(showLogin)) {
            model.addAttribute("loginHeader", "Registration Successful");
        } else {
            model.addAttribute("loginHeader", "Login");
        }

        // ✅ Add logged-in user info for layout.html
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }

        return "index";
    }

    @GetMapping("/candidate-profile")
    public String candidateProfile(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }
        return "candidate-profile";
    }

    @GetMapping("/employer-profile")
    public String employerProfile(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }
        return "employer-profile";
    }

    @GetMapping("/admin-profile")
    public String adminProfile(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }
        return "admin-profile";
    }

    @GetMapping("/job-grid")
    public String jobGrid(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }
        return "job-grid";
    }

    @GetMapping("/candidate-grid")
    public String candidateGrid(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }
        return "candidate-grid";
    }


}
