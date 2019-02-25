package com.bintech.kooswimming.controller;

import com.bintech.kooswimming.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/admin")
    public String adminView(Model model) {
        model.addAttribute("signUpList", signUpService.getAllSignUps());

        return "admin";
    }

}