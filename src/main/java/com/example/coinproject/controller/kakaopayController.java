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

import javax.servlet.http.HttpSession;

@Log                        //로그는 기록을 남기는 어노테이션
@Controller                 //컨트롤러 어노테이션
@RequiredArgsConstructor    //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Slf4j                      //로깅을 위한 어노테이션
public class kakaopayController {   //카카오 페이 결제 컨트롤러

    @Setter(onMethod_ = @Autowired) //자동 연결
    private kakaoPayService kakaopay;

    @GetMapping("/kakaoPay")    // kakaopay.mustache 뷰를 반환함.
    public String kakaoPayGet() {
        return "/kakaopay";
    }

    @PostMapping("/kakaoPay")   // POST로 방식으로 kakaoPayReady에 coin 값과 userid 값을 넘겨준다.
    public String kakaoPay(@RequestParam(name = "coin") int coin/*값으로 넘겨준 coin*/, HttpSession session) { //카카오페이 결제하기 버튼을 눌러 넘어오는 페이지
        return "redirect:" + kakaopay.kakaoPayReady(coin, (String)session.getAttribute("userid"));
    }

    @GetMapping("/kakaoPaySuccess") //kakaopay 결제가 성공했을때 호출된다.
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session, @RequestParam("coin") int coin/*주소로 보내준 파라미터*/) {//성공시 보여주는 페이지
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, coin, (String)session.getAttribute("userid")));
    }
}
