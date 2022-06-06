package com.example.coinproject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@AllArgsConstructor
@ToString
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor  //기본 생성자
public class coin_room {                    //개체
    @Column
    private String iuse;   //사용 여부 1, 0으로 표시
    @Id
    private int numroom;//방 번호
    @Column
    private int coin;   //방 코인 개수
    @Column
    private int etime;  //남은 시간
    @Column
    private LocalTime regtime;
}
