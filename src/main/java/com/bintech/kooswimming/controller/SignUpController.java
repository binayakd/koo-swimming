package com.bintech.kooswimming.controller;

import com.bintech.kooswimming.entriy.SignUp;
import com.bintech.kooswimming.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;


    @GetMapping("/")
    public String signUpForm(Model model){

        model.addAttribute("signUp", new SignUp());
        return "signup";
    }

    @PostMapping("/")
    public String signUpSubmit(@ModelAttribute SignUp signUp, Model model) {

        List<Long> idList = signUpService.createSignUp(signUp);
        model.addAttribute("id", idList.get(0));

        return "signupsubmit";
    }


}
