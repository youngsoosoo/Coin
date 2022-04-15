package com.example.coinproject.repository;

import com.example.coinproject.entity.coin_user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<coin_user, String> {
    // userid을 가지고 User 정보를 가져올 수 있게 메소드 생성
    Optional<coin_user> findByUserid(String userid);
}
