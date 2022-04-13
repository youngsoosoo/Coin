package com.example.coinproject.repository;

import com.example.coinproject.entity.coin_user;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegisterRepository extends CrudRepository<coin_user, String> {
    // username을 가지고 User 정보를 가져올 수 있게 메소드 생성
    Optional<coin_user> findByUsername(String userid);
}
