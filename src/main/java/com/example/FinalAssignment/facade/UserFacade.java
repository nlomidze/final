package com.example.FinalAssignment.facade;

import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.dto.UserDetailDto;
import com.example.FinalAssignment.model.enums.UserStatus;
import com.example.FinalAssignment.model.mapper.UserMapper;
import com.example.FinalAssignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    // --------------------- Get User by Id --------------------
    public UserDetailDto getUserById(Long userId) {
        UserEntity user = userService.findUserById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return UserMapper.toUserDetailDto(user);
    }

    // -------------------- Get All User ---------------------
    public List<UserDetailDto> getAllUser() {
        List<UserEntity> users = userService.findAllUSer();
        return UserMapper.userDetailDtoList(users);
    }

    // -------------------- Block User ------------------
    public UserDetailDto blockUser(String username) {
        UserEntity user = userService.findUserByUsername(username);
        user.setUserStatus(UserStatus.DEACTIVATED);
        return UserMapper.toUserDetailDto(user);
    }

    // -------------------- Delete User --------------------
    public void deleteUser(String username){
        userService.deleteUser(username);
    }
}
