package com.example.coinproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor //기본 생성자
public class Login {

    @Id
    private String userid;
    @Column
    private String userpw;
}
