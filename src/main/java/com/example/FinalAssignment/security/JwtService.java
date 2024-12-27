package com.example.FinalAssignment.security;

import com.example.FinalAssignment.facade.AuthFacade;
import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final AuthFacade authFacade;

    private final UserRepository userRepository;

    public UserEntity getUser(HttpServletRequest request){
        String authenticationHeader = request.getHeader("authorization");
        String token = authenticationHeader.replace("Bearer ", "");
        String username = authFacade.getUsernameFromToken(token);
        return userRepository.findUserByUsername(username).orElseThrow(()->new RuntimeException("user not found"));
    }
}
