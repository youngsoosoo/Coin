package com.example.coinproject.controller;

import com.example.coinproject.entity.coin_room;
import com.example.coinproject.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j  //로깅을 위한 어노테이션
public class RoomController {

    @Autowired // 객체 자동 연결
    private RoomRepository roomRepository;

    @GetMapping("/mainpage")
    public String Mainpage(Model model){
            List<coin_room> all = roomRepository.findAll(); //리스트에 DB정보 넣기
            model.addAttribute("list",all); //반복문을 위한 반복문 사용과 리스트 값 넘기기 위한 model
        return "mainpage";
    }
}
