package com.practice.service;

import com.practice.domain.UserCredential;
import com.practice.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserCredentialRepository userCredentialRepository;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    public Long saveUser(UserCredential userCredential) {
        userCredential.setEncodedPassword(passwordEncoder.encode(userCredential.getPassword()));
        return userCredentialRepository.save(userCredential).getId();
    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
