package com.example.coinproject.DTO;

import com.example.coinproject.entity.coin_user;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //모든 필드에 대한 생성자를 생성합니다.
@ToString           //객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴시켜줌.
public class RegisterForm {                             // userDTO
    private String userid;
    private String userpw;
    private String username;
    private Integer usercoin;

    public coin_user toEntity(){
        return new coin_user(userid, userpw, username, usercoin);
    }
}
