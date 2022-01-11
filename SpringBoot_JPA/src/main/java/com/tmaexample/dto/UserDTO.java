package com.tmaexample.dto;

import javax.persistence.Column;

public class UserDTO {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;


    public UserDTO() {}

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
