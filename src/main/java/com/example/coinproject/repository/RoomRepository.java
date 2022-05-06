package com.example.coinproject.repository;

import com.example.coinproject.entity.coin_room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<coin_room, String> {
    // numroom을 가지고 Room 정보를 가져올 수 있게 메소드 생성
    Optional<coin_room> findByNumroom(int numroom);
}
