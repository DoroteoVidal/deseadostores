package com.app.deseadostores.security;

import com.app.deseadostores.model.Role;
import com.app.deseadostores.model.User;
import com.app.deseadostores.model.UserRole;
import com.app.deseadostores.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmailIgnoreCaseAndEnabledTrue(email)
                .map(this::createSpringSecurityUser)
                .orElseThrow(
                        () -> new UsernameNotFoundException("No existe usuario con email: " + email)
                );
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user){
        List<GrantedAuthority> grantedAuthorities = user
                .getUserRoles()
                .stream()
                .map(UserRole::getRole)
                .map(Role::getType)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
