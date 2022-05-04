package com.example.coinproject.controller;

import com.example.coinproject.entity.coin_room;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@Slf4j  //로깅을 위한 어노테이션
public class RoomController {

    @Autowired // 객체 자동 연결
    private RoomRepository roomRepository;

    @GetMapping("/mainpage")
    public String Mainpage(Model model){
        coin_room room = new coin_room();
        Optional<coin_room> result = roomRepository.findByNumroom(1);
        model.addAttribute("num", result.get().getNumroom());
        return "mainpage";
    }
}
