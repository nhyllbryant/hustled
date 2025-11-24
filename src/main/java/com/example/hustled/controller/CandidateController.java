package com.example.hustled.controller;

import com.example.hustled.entity.User;
import com.example.hustled.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final UserRepository userRepository;

    public CandidateController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/candidate-grid")
    public String candidateGrid() {
        return "candidate-grid";
    }

    @GetMapping("/candidate-jobs-applied")
    public String candidateJobApplied(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
            userOpt.ifPresent(user -> model.addAttribute("loggedInUser", user));
        }
        return "candidate-jobs-applied";
    }

}
