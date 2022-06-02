package com.example.coinproject.entity;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayVO {
    private String tid, next_redirect_pc_url;
    private Date created_at;
}
