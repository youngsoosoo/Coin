package com.example.coinproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoinController {

    @GetMapping("/test")
    public String Hello(Model model){
        model.addAttribute("username", "YoungSoo");
        return "login";
    }

    @GetMapping("/test1")
    public String Bye(Model model){
        model.addAttribute("username", "YoungSoo");
        return "register";
    }
}
