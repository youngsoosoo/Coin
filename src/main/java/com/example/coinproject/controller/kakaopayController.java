package com.example.coinproject.controller;

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
    public String kakaoPay() {
        log.info("kakaoPay post............................................");

        return "redirect:" + kakaopay.kakaoPayReady();

    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));

    }
}
