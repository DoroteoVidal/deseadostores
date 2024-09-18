package com.app.deseadostores.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class PrincipalProvider {

    public Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
