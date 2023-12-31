package com.practice.controller;

import com.practice.domain.UserCredential;
import com.practice.dto.AuthRequest;
import com.practice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/api")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Long> saveUser(@RequestBody UserCredential userCredential) {
        return ResponseEntity.ok(authService.saveUser(userCredential));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.ok(authService.generateToken(authRequest.getEmail()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 사용자입니다.");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        authService.validateToken(token);
        return ResponseEntity.ok("토큰이 검증 성공");
    }

    @GetMapping("/user-info")
    public ResponseEntity<String> getEmailFromAuthentication(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(email);
    }
}
