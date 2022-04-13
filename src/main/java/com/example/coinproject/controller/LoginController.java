package com.example.coinproject.controller;

import com.example.coinproject.DTO.LoginForm;
import com.example.coinproject.DTO.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String Login(){
        return"login";
    }

    @PostMapping("/loginaction")
    public String loginAction(LoginForm form){
        System.out.println(form.toString());

        // 1. DTO 변환

        // 2. Repository에게 엔티티를 DB안에 저장하게 함
        return "";
    }
}