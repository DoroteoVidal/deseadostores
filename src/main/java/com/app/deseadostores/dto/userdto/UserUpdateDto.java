package com.app.deseadostores.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Long dni;

    private Long phone;

    public UserUpdateDto() {}

    public UserUpdateDto(String name, String lastname, String address,
                         LocalDate dateOfBirth, Long dni, Long phone) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
        this.phone = phone;
    }
}
