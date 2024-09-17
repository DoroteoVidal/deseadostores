package com.app.deseadostores.service.impl;

import com.app.deseadostores.dto.userdto.UserRequestDto;
import com.app.deseadostores.dto.userdto.UserResponseDto;
import com.app.deseadostores.exception.UserNotFoundException;
import com.app.deseadostores.model.User;
import com.app.deseadostores.repository.UserRepository;
import com.app.deseadostores.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final int ALLOWED_AGE = 18;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

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

        if(userRepository.findUserByEmailIgnoreCase(userDto.getEmail()).isPresent()) {
            throw new UserNotFoundException("El usuario con este email ya existe");
        }

        if(Period.between(userDto.getDateOfBirth(), LocalDate.now()).getYears() < ALLOWED_AGE) {
            throw new UserNotFoundException("El usuario que desea registrar es menor de edad");
        }

        return mapper
                .map(userRepository
                        .save(mapper
                                .map(userDto, User.class))
                        , UserResponseDto.class);
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
            throw new UserNotFoundException("El usuario que desea actualizar es menor de edad");
        }

        BeanUtils.copyProperties(userDto, dbUser);
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
}
