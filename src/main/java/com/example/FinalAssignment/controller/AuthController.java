package com.example.FinalAssignment.controller;

import com.example.FinalAssignment.facade.AuthFacade;
import com.example.FinalAssignment.model.dto.ResponseTokensDto;
import com.example.FinalAssignment.model.dto.UserDetailDto;
import com.example.FinalAssignment.model.param.LoginParam;
import com.example.FinalAssignment.model.param.SignUpParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/signup") //anonymous
    public ResponseEntity<UserDetailDto> singUp(@RequestBody @Valid SignUpParam signUpParam) {
        return new ResponseEntity<>(authFacade.singUp(signUpParam), HttpStatus.CREATED);
    }

    @PostMapping("/login") //anonymous
    public ResponseEntity<ResponseTokensDto> login(@RequestBody @Valid LoginParam loginParam) {
        return new ResponseEntity<>(authFacade.login(loginParam), HttpStatus.OK);
    }

}
