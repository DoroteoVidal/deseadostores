package com.app.deseadostores.service.impl;

import com.app.deseadostores.dto.userdto.*;
import com.app.deseadostores.exception.UserNotFoundException;
import com.app.deseadostores.model.Role;
import com.app.deseadostores.model.User;
import com.app.deseadostores.model.UserRole;
import com.app.deseadostores.repository.RoleRepository;
import com.app.deseadostores.repository.UserRepository;
import com.app.deseadostores.security.PrincipalProvider;
import com.app.deseadostores.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final int ALLOWED_AGE = 18;

    public static final Long USER_ID = 2L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PrincipalProvider principalProvider;

    @Override
    public User getActualUser() throws UserNotFoundException {
        return userRepository
                .findUserByEmailIgnoreCaseAndEnabledTrue(principalProvider
                        .getPrincipal()
                        .getName())
                .orElseThrow(() -> new UserNotFoundException("No existe usuario logueado"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(Long userId) throws UserNotFoundException {
        return mapper
                .map(userRepository
                        .findByIdAndEnabledTrue(userId)
                        .orElseThrow(UserNotFoundException::new)
                        , UserResponseDto.class);
    }
    @Override
    @Transactional(readOnly = true)
    public Set<UserResponseDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> mapper.map(u, UserResponseDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public UserResponseDto save(UserRequestDto userDto) throws UserNotFoundException {
        if(!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            throw new UserNotFoundException("Las contraseñas no son identicas, intente nuevamente");
        }

        if(userRepository.findUserByEmailIgnoreCase(userDto.getEmail()).isPresent()) {
            throw new UserNotFoundException("Ya existe un usuario con este email, intente nuevamente");
        }

        if(Period.between(userDto.getDateOfBirth(), LocalDate.now()).getYears() < ALLOWED_AGE) {
            throw new UserNotFoundException("La fecha ingresada no es valida, el usuario debe ser mayor de edad");
        }

        User user = mapper.map(userDto, User.class);
        Role role = roleRepository
                .findById(USER_ID)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        Set<UserRole> roles = new HashSet<>();
        UserRole userRole = new UserRole(user, role);
        roles.add(userRole);
        user.setUserRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);

        return mapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Long userId, UserRequestDto userDto) throws UserNotFoundException {
        User dbUser = userRepository
                .findByIdAndEnabledTrue(userId)
                .orElseThrow(UserNotFoundException::new);
        if(!dbUser.getEmail().equals(userDto.getEmail())) {
            if(userRepository.findUserByEmailIgnoreCase(userDto.getEmail()).isPresent()) {
                throw new UserNotFoundException("Ya existe un usuario con este email, intente nuevamente");
            }
        }
        if(Period.between(userDto.getDateOfBirth(), LocalDate.now()).getYears() < ALLOWED_AGE) {
            throw new UserNotFoundException("La fecha ingresada no es valida, el usuario debe ser mayor de edad");
        }

        BeanUtils.copyProperties(userDto, dbUser);
        dbUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User updatedUser = userRepository.save(dbUser);

        return mapper.map(updatedUser, UserResponseDto.class);
    }

    @Override
    public void delete(Long userId) throws UserNotFoundException {
        User user = userRepository
                .findByIdAndEnabledTrue(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public UserResponseDto updateMyUserInformation(UserUpdateDto userDto) throws UserNotFoundException {
        User myUser = this.getActualUser();

        if(Period.between(userDto.getDateOfBirth(), LocalDate.now()).getYears() < ALLOWED_AGE) {
            throw new UserNotFoundException("La fecha ingresada no es valida, el usuario debe ser mayor de edad");
        }

        BeanUtils.copyProperties(userDto, myUser);
        User updatedUser = userRepository.save(myUser);

        return mapper.map(updatedUser, UserResponseDto.class);
    }

    @Override
    public UserCredentialsDto getMyUserCredentials() throws UserNotFoundException {
        User myUser = this.getActualUser();

        return new UserCredentialsDto(myUser.getEmail(), myUser.getPassword());
    }

    @Override
    public void updateMyUserCredentials(UserUpdateCredentialsDto userCredentialsDto) throws UserNotFoundException {
        if(!userCredentialsDto.getEmail().equals(userCredentialsDto.getEmailConfirmation())) {
            throw new UserNotFoundException("Los emails no son identicos, intente nuevamente");
        }

        if(!userCredentialsDto.getPassword().equals(userCredentialsDto.getPasswordConfirm())) {
            throw new UserNotFoundException("Las contraseñas no son identicas, intente nuevamente");
        }

        User myUser = this.getActualUser();

        myUser.setEmail(userCredentialsDto.getEmail());
        myUser.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));
        userRepository.save(myUser);
    }

    @Override
    public void deleteMyUser() throws UserNotFoundException {
        User myUser = this.getActualUser();
        myUser.setEnabled(false);
        userRepository.save(myUser);
    }
}
