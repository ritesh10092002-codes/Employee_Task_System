package com.example.Security.DTO;

import com.example.Security.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class signUpRequest {

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role;
}
