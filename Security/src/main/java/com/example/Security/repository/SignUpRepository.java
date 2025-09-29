package com.example.Security.repository;

import com.example.Security.entity.sign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpRepository extends JpaRepository<sign,Long> {

    //optional is mandatory as it may retur null value also
     public Optional<sign> findByEmail(String email);
}
