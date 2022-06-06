package com.example.coinproject.DTO;

import com.example.coinproject.entity.coin_room;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@AllArgsConstructor
@ToString
public class RoomForm {                         //rootDTO
    private String iuse;
    private int numroom;
    private int coin;
    private int etime;
    private LocalTime regtime;

    public coin_room toEntity(){
        return new coin_room(iuse, numroom, coin, etime, regtime);
    }
}
