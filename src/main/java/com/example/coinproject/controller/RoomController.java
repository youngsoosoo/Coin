package com.example.coinproject.controller;

import com.example.coinproject.DTO.RoomForm;
import com.example.coinproject.entity.coin_room;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import com.example.coinproject.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j  //로깅을 위한 어노테이션
public class RoomController {


    @Autowired // 객체 자동 연결
    private RoomRepository roomRepository;
    @Autowired
    private RegisterRepository registerRepository;

    @GetMapping("/mainpage")
    public String Mainpage(Model model){        //메인페이지 노래방 테이블 출력
        coin_room room = new coin_room();
        LocalTime currentTime = LocalTime.now();
        LocalTime targetTime = LocalTime.of(00, 00, 00);
        List<coin_room> all = roomRepository.findAll(); //리스트에 DB정보 넣기
        model.addAttribute("list",all); //반복문을 위한 반복문 사용과 리스트 값 넘기기 위한 model
        List<coin_room> list = all.stream().filter(x -> x.getIuse().equals("사용중")).collect(Collectors.toList());//사용중인 노래방을 리스트 담는 것
        for(int i=0; i<list.size(); i++){
            room.setNumroom(list.get(i).getNumroom());
            if(currentTime.isAfter(all.get(i).getRegtime().plusMinutes(3))) {//여기부분고쳐야함 시간 넘기는 거
                log.info("6");
                int coin = all.get(i).getCoin();
                if (coin <= 1) {
                    room.setCoin(0);
                    room.setEtime(0);
                    room.setIuse("사용가능");
                    room.setRegtime(targetTime);
                }else {
                    room.setCoin(coin - 1);
                    room.setEtime((coin - 1) * 3);
                    room.setIuse("사용중");
                    room.setRegtime(all.get(i).getRegtime().plusMinutes(3));
                }
                log.info("3");
                roomRepository.save(room);
            }
        }
        return "mainpage";
    }

    @GetMapping("/coincharge")
    public String charge(){
        return "/coincharge";
    }

    @PostMapping("coincharge")
    public void coinchargeSuccess(@RequestParam("numroom") int numroom, @RequestParam("iuse") String iuse, @RequestParam("coin") int coin, @RequestParam("etime") int etime, Model model){
        model.addAttribute("numroom", numroom);
        model.addAttribute("iuse", iuse);
        model.addAttribute("coin", coin);
        model.addAttribute("etime", etime);
    }

    @PostMapping("/use")
    public String Use(RoomForm form, HttpSession session){  //코인 충전
        coin_user user = new coin_user();
        coin_room room = form.toEntity();
        Optional<coin_user> result_user = registerRepository.findByUserid((String) session.getAttribute("userid"));
        Optional<coin_room> result_room = roomRepository.findByNumroom(form.toEntity().getNumroom());
        room.setNumroom(form.toEntity().getNumroom());

        int hour=0;
        int coin = result_room.get().getCoin() + form.toEntity().getCoin(); // 원래 방에 존재하는 코인 개수와의 합
        int minute = coin * 3;  //etime 시간 계산
        int inputcoin = result_user.get().getUsercoin() - form.toEntity().getCoin();    //아이디가 보유하고 있는 코인을 방에 넣어줌

        LocalTime currentTime = LocalTime.now();   //현재 시간 now()
        LocalTime targetTime = LocalTime.of(23, 00);    //마감 시간

        //현재 시간
        if(minute >= 60){
            minute = minute - 60;
            hour++;
        }
        if(form.toEntity().getCoin() > 0 & !currentTime.plusHours(hour).plusMinutes(minute).isAfter(targetTime)){ //시간이 지났는지 안 지났는지와 코인이 0개 이상일 때
            room.setCoin(coin);
            room.setEtime(minute);
            room.setIuse("사용중");
            room.setRegtime(currentTime);
            user.setUserid(result_user.get().getUserid());
            if(result_user.get().getUsercoin() < 1){
                return "coinfail";
            }
            user.setUsercoin(inputcoin);
            user.setUsername(result_user.get().getUsername());
            user.setUserpw(result_user.get().getUserpw());
            roomRepository.save(room);
            registerRepository.save(user);
            return "redirect:/mainpage";
        }else if(form.toEntity().getCoin() <= 0){
            return "coinfail";
        }
        return "coinfail";
    }

    @GetMapping("/map")
    public String Map(){    //지도
        return "map";
    }
}
