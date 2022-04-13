package com.example.coinproject.controller;

import com.example.coinproject.DTO.LoginForm;
import com.example.coinproject.entity.Login;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.LoginRepository;
import com.example.coinproject.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired // 객체 자동 연결
    private RegisterRepository registerRepository;

    @GetMapping("/login")
    public String Login(){
        return"login";
    }

    @PostMapping("/loginaction")
    public String loginAction(coin_user user, Model model){
        String userid = user.getUserid();
        String userpw = user.getUserpw();
        user.setUserid(user.getUserid());
        user.setUserpw(user.getUserpw());
        registerRepository.save(user);
        model.addAttribute("username" , user.getUserid());
        return "mainpage";
    }
}