package com.example.FinalAssignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    // protected - visible is in same class, package and outside package only from child class
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;
}
