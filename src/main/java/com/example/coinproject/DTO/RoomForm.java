package com.example.coinproject.DTO;

import com.example.coinproject.entity.coin_room;
import com.example.coinproject.entity.coin_user;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class RoomForm {
    private String iuse;
    private int numroom;
    private int coin;
    private int etime;

    public coin_room toEntity(){
        return new coin_room(iuse, numroom, coin, etime);
    }
}
