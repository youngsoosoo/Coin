package com.example.coinproject.controller;

import com.example.coinproject.DTO.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String Register(){
        return"register";
    }

    @PostMapping("/registeraction")
    public String registerAction(RegisterForm form){
        System.out.println(form.toString());
        return "";
    }
}
