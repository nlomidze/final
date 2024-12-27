package com.example.FinalAssignment.model.enums;

public enum UserRole {
    ROLE_USER,
    ROLE_ARTIST,
    ROLE_ADMIN;

    public static boolean isValidRegistrationRole(UserRole role) {
        return role == ROLE_USER || role == ROLE_ARTIST;
    }
}
