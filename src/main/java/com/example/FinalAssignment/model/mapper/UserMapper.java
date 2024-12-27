package com.example.FinalAssignment.model.mapper;

import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.dto.UserDetailDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    // ---------------- Map User Entity to User Detail Dto ------------------------
    public static UserDetailDto toUserDetailDto(UserEntity userEntity) {
        return new UserDetailDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getRole(),
                userEntity.getUserStatus()
        );
    }

    // ------------------------- UserEntity List to UserDetailsDto List -----------------------
    public static List<UserDetailDto> userDetailDtoList(List<UserEntity> allUser) {
        return allUser.stream()
                .map(user ->
                        new UserDetailDto(
                                user.getId(),
                                user.getUsername(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getEmail(),
                                user.getRole(),
                                user.getUserStatus()
                        )
                )
                .collect(Collectors.toList());
    }
}
