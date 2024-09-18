package com.app.deseadostores.dto.userdto;

import lombok.Getter;

@Getter
public class UserCredentialsDto {
    private String email;
    private String password;

    public UserCredentialsDto() {}

    public UserCredentialsDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
