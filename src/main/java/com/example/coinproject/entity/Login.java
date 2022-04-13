package com.example.coinproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Login {
    @Column
    private String userid;
    @Column
    private String userpw;
}
