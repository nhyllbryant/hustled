package com.example.hustled.controller;

import com.example.hustled.entity.User;
import com.example.hustled.repository.UserRepository;
import com.example.hustled.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserService userService;

    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("loginError", "User does not exist.");
            return "redirect:/index?showLogin=true";
        }

        User user = userOpt.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("loginError", "Invalid password.");
            return "redirect:/index?showLogin=true";
        }

        session.setAttribute("loggedInUser", user);

        switch (user.getRole()) {
            case "CANDIDATE":
                return "redirect:/candidate-profile";
            case "EMPLOYER":
                return "redirect:/employer-profile";
            case "ADMIN":
                return "redirect:/admin-profile";
            default:
                return "redirect:/index";
        }
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

    @GetMapping("/manual-logout")
    public String manualLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }
}
