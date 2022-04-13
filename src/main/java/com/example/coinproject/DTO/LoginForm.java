package com.example.coinproject.DTO;

public class LoginForm {
    private String userid;
    private String userpw;

    public LoginForm(String userid, String userpw) {
        this.userid = userid;
        this.userpw = userpw;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "userid='" + userid + '\'' +
                ", userpw='" + userpw + '\'' +
                '}';
    }
}
