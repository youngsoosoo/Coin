package com.example.coinproject.controller;

import com.example.coinproject.DTO.RegisterForm;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import com.example.coinproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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
    public String Mainpage(){
        return "mainpage";
    }

    @GetMapping("/mypage")
    public String Mypage(){
        return "mypage";
    }

    @GetMapping("/update")
    public String Update(){

        return "redirect:/my";
    }

    @GetMapping("/my")
    public String My(){
        return "my";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userid");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginAction(RegisterForm form, HttpSession session){

        coin_user user = form.toEntity();
        int total = loginService.login(user);
        Optional<coin_user> result_id = registerRepository.findByUserid(user.getUserid());

        if(total == 0) {
            return "redirect:/login";
        } else{
            System.out.println("로그인 성공");
            session.setAttribute("userid", user.getUserid());
            session.setAttribute("username", result_id.get().getUsername());
            session.setAttribute("usercoin", result_id.get().getUsercoin());
            return "redirect:/mainpage";
        }
    }
}