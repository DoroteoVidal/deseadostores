package com.app.deseadostores.service;

import com.app.deseadostores.dto.userdto.*;
import com.app.deseadostores.model.User;

public interface UserService extends BaseService<UserResponseDto, UserRequestDto> {

    User getActualUser() throws Exception;

    UserResponseDto updateMyUserInformation(UserUpdateDto userDto) throws Exception;

    UserCredentialsDto getMyUserCredentials() throws Exception;

    void updateMyUserCredentials(UserUpdateCredentialsDto userCredentialsDto) throws Exception;

    void deleteMyUser() throws Exception;
}
