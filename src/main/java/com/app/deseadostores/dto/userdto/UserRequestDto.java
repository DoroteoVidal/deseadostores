package com.app.deseadostores.dto.userdto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserRequestDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private LocalDate dateOfBirth;
    private Long dni;
    private Long phone;

    public UserRequestDto() {}
}
