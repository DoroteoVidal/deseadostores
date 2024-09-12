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

    public UserRequestDto(String name, String lastname, String email,
                          String password, String address, LocalDate dateOfBirth,
                          Long dni, Long phone) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
        this.phone = phone;
    }
}
