package com.example.coinproject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@ToString
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor  //기본 생성자
public class coin_user {
    @Id
    private String userid;
    @Column
    private String userpw;
    @Column
    private String username;
    @Column
    private Integer usercoin = 0;
}
