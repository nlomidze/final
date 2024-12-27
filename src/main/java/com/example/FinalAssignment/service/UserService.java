package com.example.FinalAssignment.service;

import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // ----------------- Save User In Data base -------------------
    public UserEntity saveUserInDatabase(UserEntity user) {
        return userRepository.save(user);
    }

    // --------------------- Find User by username --------------------
    public UserEntity findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User or password is incorrect"));
    }


    // --------------------- Find User by Id --------------------
    public Optional<UserEntity> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // ---------------------- Find All User -----------------------
    public List<UserEntity> findAllUSer() {
        return userRepository.findAll();
    }

    // ---------------------- Delete User ------------------------
    public void deleteUser(String username){
        userRepository.deleteUser(username);
    }

}
