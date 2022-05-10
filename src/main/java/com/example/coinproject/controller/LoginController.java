package com.example.coinproject.controller;

import com.example.coinproject.DTO.RegisterForm;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import com.example.coinproject.service.LoginService;
import com.example.coinproject.service.Mypageupdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/mypage")
    public String Mypage(){
        return "mypage";
    }

    @PostMapping("/update")
    public String Update(RegisterForm form){
        coin_user user = new coin_user();
        Optional<coin_user> result_id = registerRepository.findByUserid(form.toEntity().getUserid());   //DB에서 불러온 coin_user
        user.setUserid(form.toEntity().getUserid());    //userid는 바꿀 수 없다.
        if(form.toEntity().getUsername() == ""){        //입력하지 않았다면 DB에 있던 원래 값을 다시 넣어준다.
            user.setUsername((String) result_id.get().getUsername());
        }else{                                          // 입력 값이 있다면 입력 값으로 바꿔준다
            user.setUsername(form.toEntity().getUsername());
        }
        if(form.toEntity().getUserpw() == ""){          //입력하지 않았다면 DB에 있던 원래 값을 다시 넣어준다.
            user.setUserpw((String) result_id.get().getUserpw());
        }else{                                          // 입력 값이 있다면 입력 값으로 바꿔준다
            user.setUserpw(form.toEntity().getUserpw());
        }
        if(form.toEntity().getUsercoin() == null){      //입력하지 않았다면 DB에 있던 원래 값을 다시 넣어준다.
            user.setUsercoin(result_id.get().getUsercoin());
        }
        else{                                           //입력 값이 있다면 원래 있던 코인의 수와 더해준다.
            Integer coin = (Integer) result_id.get().getUsercoin() + form.toEntity().getUsercoin();
            user.setUsercoin(coin);
        }
        registerRepository.save(user);                  // DB에 저장해준다
        return "redirect:/my";
    }


    @GetMapping("/my")
    public String My(Model model, HttpSession session){
        Optional<coin_user> result_id = registerRepository.findByUserid((String) session.getAttribute("userid"));
        model.addAttribute("username", result_id.get().getUsername());
        model.addAttribute("usercoin", result_id.get().getUsercoin());
        return "my";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userid");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginAction(RegisterForm form, HttpSession session){

        coin_user user = form.toEntity();   //form으로 받은 개체 값을 coin_user에 넣어준다.
        int total = loginService.login(user);   //loginService의 login 함수를 호출해준다.

        if(total == 0) {
            return "redirect:/login";
        } else{
            System.out.println("로그인 성공");
            session.setAttribute("userid", user.getUserid());
            return "redirect:/mainpage";
        }
    }
}