package com.example.coinproject.DTO;

import com.example.coinproject.entity.coin_user;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class RegisterForm {
    private String userid;
    private String userpw;
    private String username;
    private Integer usercoin = 0;

    public coin_user toEntity(){
        return new coin_user(userid, userpw, username, 0);
    }
}
