package com.tmaexample.dto;

import javax.persistence.Column;

public class LogonDTO {
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public LogonDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public LogonDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
