package com.example.Security.controller;


import com.example.Security.DTO.loginRequest;
import com.example.Security.DTO.signUpRequest;
import com.example.Security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupByRole(@RequestBody signUpRequest sign){
       String response= authService.signupbyrole(sign);

       return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody loginRequest loginRequest){
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }
}
