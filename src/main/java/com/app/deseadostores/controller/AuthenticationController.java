package com.app.deseadostores.controller;

import com.app.deseadostores.dto.authDto.AuthDto;
import com.app.deseadostores.dto.jwtDto.JwtTokenDto;
import com.app.deseadostores.dto.userdto.UserRequestDto;
import com.app.deseadostores.exception.UserNotFoundException;
import com.app.deseadostores.security.JwtFilter;
import com.app.deseadostores.security.TokenProvider;
import com.app.deseadostores.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid UserRequestDto userDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(userDto));
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("log-in")
    public ResponseEntity<?> logIn(@RequestBody @Valid AuthDto authDto) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final var jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            return new ResponseEntity<>(new JwtTokenDto(jwt), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
