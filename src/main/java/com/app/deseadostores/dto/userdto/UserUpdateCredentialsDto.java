package com.app.deseadostores.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateCredentialsDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Email
    private String emailConfirmation;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;

    public UserUpdateCredentialsDto() {}

    public UserUpdateCredentialsDto(String email, String emailConfirmation,
                                    String password, String passwordConfirm) {
        this.email = email;
        this.emailConfirmation = emailConfirmation;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
