package com.example.Task_Manager.controller;

import com.example.Task_Manager.Utils.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Контроллер для аутентификации пользователей.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            logger.info("Аутентификация прошла успешно для пользователя: " + authRequest.getUsername());
            String token = jwtUtil.generateToken(authRequest.getUsername());
            logger.info("Токен пользователя" + token);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @Getter
    @Setter
    static class AuthRequest {
        private String username;
        private String password;
    }
}
