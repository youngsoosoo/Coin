package com.example.coinproject.controller;

import com.example.coinproject.DTO.RegisterForm;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import com.example.coinproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired // 객체 자동 연결
    private RegisterRepository registerRepository;

    @GetMapping("/login")
    public String Login(){
        return "login";
    }

    @GetMapping("/mainpage")
    public String Mainpage(Model model){
        model.addAttribute("userid", "YoungSoo");
        return "mainpage";
    }

    @GetMapping("/mypage")
    public String Mypage(){
        return "mypage";
    }


    @PostMapping("/loginaction")
    public String loginAction(RegisterForm form){
        log.info(form.toString());

        coin_user user = form.toEntity();

        log.info(user.toString());
        int total = loginService.login(user);
        log.info(String.valueOf(total));
        if(total == 0) {
            return "login";
        } else{
            return "mainpage";
        }
    }
}