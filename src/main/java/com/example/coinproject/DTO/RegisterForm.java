package com.example.coinproject.DTO;

public class RegisterForm {
    private String userid;
    private String userpw;
    private String username;

    public RegisterForm(String userid, String userpw, String username) {
        this.userid = userid;
        this.userpw = userpw;
        this.username = username;
    }

    @Override
    public String toString() {
        return "registerForm{" +
                "userid='" + userid + '\'' +
                ", userpw='" + userpw + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
