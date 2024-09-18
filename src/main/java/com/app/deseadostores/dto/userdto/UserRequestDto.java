package com.app.deseadostores.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Long dni;

    private Long phone;

    public UserRequestDto() {}

    public UserRequestDto(String name, String lastname, String email,
                          String password, String passwordConfirm, String address, LocalDate dateOfBirth,
                          Long dni, Long phone) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
        this.phone = phone;
    }
}
