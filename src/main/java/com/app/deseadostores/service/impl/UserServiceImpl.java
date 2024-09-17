package com.app.deseadostores.service.impl;

import com.app.deseadostores.dto.userdto.UserRequestDto;
import com.app.deseadostores.dto.userdto.UserResponseDto;
import com.app.deseadostores.model.User;
import com.app.deseadostores.repository.UserRepository;
import com.app.deseadostores.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
    public UserResponseDto getById(Long id) throws NotFoundException {
        return mapper
                .map(userRepository
                        .findByIdAndEnabledTrue(id)
                        .orElseThrow(NotFoundException::new)
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
    public UserResponseDto save(UserRequestDto entityRequest) throws Exception {

        if(userRepository.findUserByEmailIgnoreCase(entityRequest.getEmail()).isPresent()) {
            throw new Exception("El usuario con este email ya existe");
        }

        if(Period.between(entityRequest.getDateOfBirth(), LocalDate.now()).getYears() < ALLOWED_AGE) {
            throw new Exception("El usuario que desea registrar es menor de edad");
        }

        return mapper
                .map(userRepository
                        .save(mapper
                                .map(entityRequest, User.class))
                        , UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto entityRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
