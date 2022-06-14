package com.example.coinproject.controller;

import com.example.coinproject.DTO.RegisterForm;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j  //로깅을 위한 어노테이션
public class RegisterController {

    @Autowired // 객체 자동 연결
    private RegisterRepository registerRepository;

    @GetMapping("/register")//회원가입 페이지
    public String Register(){
        return"register";
    }

    @PostMapping("/registeraction")//회원가입 페이지 백엔드
    public String registerAction(RegisterForm form){
        log.info(form.toString());
        //System.out.println(form.toString()); --> 로깅기능으로 대체
        // 1. DTO 변환
        coin_user user = form.toEntity();
        user.setUsercoin(0);
        log.info(user.toString());

        // 2. Repository에게 엔티티를 DB안에 저장하게 함
        coin_user saved = registerRepository.save(user);
        log.info(saved.toString());
        return "login";
    }
}
