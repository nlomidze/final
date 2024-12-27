package com.example.FinalAssignment.facade;

import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.dto.ResponseTokensDto;
import com.example.FinalAssignment.model.dto.UserDetailDto;
import com.example.FinalAssignment.model.enums.UserRole;
import com.example.FinalAssignment.model.enums.UserStatus;
import com.example.FinalAssignment.model.mapper.ResponseTokensMapper;
import com.example.FinalAssignment.model.mapper.UserMapper;
import com.example.FinalAssignment.model.param.LoginParam;
import com.example.FinalAssignment.model.param.SignUpParam;
import com.example.FinalAssignment.security.CustomAuthentication;
import com.example.FinalAssignment.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.AssertTrue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public static final String secret = "21351653416531GP6315531653955955";


    // ------------------------- Sign Up -------------------------
    public UserDetailDto singUp(SignUpParam signUpParam) {
        // Check Valid Role
        if (!UserRole.isValidRegistrationRole(signUpParam.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You can only choose ROLE_USER or ROLE_ARTIST for registration."
            );
        }

        UserEntity user = new UserEntity();
        user.setUsername(signUpParam.getUsername());
        user.setFirstName(signUpParam.getFirstName());
        user.setLastName(signUpParam.getLastName());
        user.setEmail(signUpParam.getEmail());
        user.setRole(signUpParam.getRole());
        user.setPassword(passwordEncoder.encode(signUpParam.getPassword()));
        user.setUserStatus(UserStatus.ACTIVE);
        user = userService.saveUserInDatabase(user);
        return UserMapper.toUserDetailDto(user);
    }

    // ------------------------- Log In ---------------------------
    public ResponseTokensDto login(LoginParam loginParam) {
        UserEntity user = userService.findUserByUsername(loginParam.getUsername());

        // Check password
        boolean matches = passwordEncoder.matches(loginParam.getPassword(), user.getPassword());
        if (!matches) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User or password is incorrect");
        }

        // Get Token
        String token = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .claim("firstName", user.getFirstName())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

        return ResponseTokensMapper.toResponseTokensDto(token, 3600L);
    }

    // -- Authenticate ---
    public Authentication authenticate(String token) {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token);
        Claims payload = claimsJws.getPayload();
        String username = payload.get("username", String.class);
        String role = payload.get("role", String.class);
        Long id = payload.get("id", Long.class);
        return new CustomAuthentication(role, username, id);
    }

    // -- getInfoFromToken ---
    public String getUsernameFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token);
        Claims payload = claimsJws.getPayload();
        return payload.get("username", String.class);
    }

    // -------------------- Helper ----------------------
    @AssertTrue(message = "Invalid role. Only ROLE_USER or ROLE_ARTIST are allowed.")
    public boolean isRoleValid(UserRole role) {
        return role == UserRole.ROLE_USER || role == UserRole.ROLE_ARTIST;
    }
}
