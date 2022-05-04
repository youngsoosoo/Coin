package com.example.coinproject.service;

import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class Mypageupdate {

    private final RegisterRepository registerRepository;

    public void update(coin_user user) {
        user.setUserid(user.getUserid());
        user.setUserpw(user.getUserpw());
        user.setUsername(user.getUsername());
        user.setUsercoin(user.getUsercoin());
        registerRepository.save(user);
    }
}
