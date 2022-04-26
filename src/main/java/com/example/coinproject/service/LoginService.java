package com.example.coinproject.service;

import com.example.coinproject.DTO.RegisterForm;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {
    private final RegisterRepository registerRepository;

    public int login(coin_user user) {
        Optional<coin_user> result_id = registerRepository.findByUserid(user.getUserid());
        log.info(result_id.get().getUserid());
        log.info(result_id.get().getUserpw());

        if (!result_id.get().getUserid().equals(user.getUserid())) {
            return 0;
        }
        if(!result_id.get().getUserpw().equals(user.getUserpw())) {
            return 0;
        }
        return 1;
    }
}
