package com.example.hustled.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidates")
public class CandidateController {


    @GetMapping("/candidate-grid")
    public String candidateGrid() {
        return "candidate-grid";
    }

}
