package com.app.deseadostores.service.impl;

import com.app.deseadostores.dto.userdto.UserRequestDto;
import com.app.deseadostores.dto.userdto.UserResponseDto;
import com.app.deseadostores.repository.UserRepository;
import com.app.deseadostores.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(Long id) throws NotFoundException {
        return mapper
                .map(userRepository
                        .findById(id)
                        .orElseThrow(NotFoundException::new), UserResponseDto.class);
    }

    @Override
    public Set<UserResponseDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> mapper.map(u, UserResponseDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserResponseDto save(UserRequestDto entityRequest) {
        return null;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto entityRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
