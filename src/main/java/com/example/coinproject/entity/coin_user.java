package com.example.coinproject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@ToString
@Entity     //여러 엔티티간 연관관계를 정의
@Builder    //복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
@Getter
@Setter
@NoArgsConstructor  //기본 생성자
public class coin_user {                    //개체
    @Id
    private String userid;
    @Column
    private String userpw;
    @Column
    private String username;
    @Column
    private Integer usercoin;
}
