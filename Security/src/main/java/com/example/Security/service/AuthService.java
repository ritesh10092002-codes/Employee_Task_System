package com.example.Security.service;

import com.example.Security.DTO.loginRequest;
import com.example.Security.DTO.signUpRequest;
import com.example.Security.ROLE;
import com.example.Security.entity.sign;
import com.example.Security.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SignUpRepository signUpRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(loginRequest loginRequest) {

        Optional<sign> userOpt = signUpRepository.findByEmail(loginRequest.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found";
        }

        sign user = userOpt.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return "Invalid password";
        }

        // Generate JWT
        String token = jwtService.generateToken(user);

        return token;
    }

    public String  signupbyrole(signUpRequest signRequest) {




        if(signUpRepository.findByEmail(signRequest.getEmail()).isPresent()){
            return "User with email "+signRequest.getEmail()+" already exists";
        }

        sign s = new sign();
        s.setEmail(signRequest.getEmail());
        s.setPassword(passwordEncoder.encode(signRequest.getPassword()));
        s.setRole(ROLE.valueOf(signRequest.getRole().toString()));

        signUpRepository.save(s);

        return "User successfully registered";

    }
}
