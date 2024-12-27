package com.example.FinalAssignment.controller;

import com.example.FinalAssignment.facade.UserFacade;
import com.example.FinalAssignment.model.dto.UserDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{userId}") //userId or admin
    @PreAuthorize("hasRole('ADMIN') or @UserValidator.checkOwnership(#userId)")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<UserDetailDto> getUserDetailDto(@PathVariable Long userId) {
        return new ResponseEntity<>(userFacade.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("get-all-user")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<List<UserDetailDto>> getAllUser() {
        return new ResponseEntity<>(userFacade.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("block-user")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<UserDetailDto> deactivateUser(String username) {
        return new ResponseEntity<>(userFacade.blockUser(username), HttpStatus.OK);
    }

    @DeleteMapping ("delete-user")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public void deleteUser(String username) {
        userFacade.deleteUser(username);
    }
}

