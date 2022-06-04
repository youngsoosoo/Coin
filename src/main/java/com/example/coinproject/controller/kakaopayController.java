package com.example.coinproject.controller;

import com.example.coinproject.DTO.RegisterForm;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.service.kakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Log
@Controller
@RequiredArgsConstructor
@Slf4j
public class kakaopayController {

    @Setter(onMethod_ = @Autowired)
    private kakaoPayService kakaopay;

    @GetMapping("/kakaoPay")
    public String kakaoPayGet() {
        return "/kakaopay";
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam(name = "coin") int coin/*값으로 넘겨준 coin*/, HttpSession session) { //카카오페이 결제하기 버튼을 눌러 넘어오는 페이지
        return "redirect:" + kakaopay.kakaoPayReady(coin, (String)session.getAttribute("userid"));
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session, @RequestParam("coin") int coin/*주소로 보내준 파라미터*/) {//성공시 보여주는 페이지
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, coin, (String)session.getAttribute("userid")));
    }
}
