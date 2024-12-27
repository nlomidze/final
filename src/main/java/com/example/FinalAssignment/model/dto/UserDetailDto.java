package com.example.FinalAssignment.model.dto;

import com.example.FinalAssignment.model.enums.UserRole;
import com.example.FinalAssignment.model.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailDto extends UserDto {
    private Long userId;
    private UserStatus userStatus;
    private UserRole role;

    public UserDetailDto(
            Long userId,
            String username,
            String firstName,
            String lastName,
            String email,
            UserRole role,
            UserStatus userStatus
    ) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userStatus = userStatus;
        this.role = role;
    }
}
