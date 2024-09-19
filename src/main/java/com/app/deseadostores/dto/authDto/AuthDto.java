package com.app.deseadostores.dto.authDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthDto {
    private String email;
    private String password;
}