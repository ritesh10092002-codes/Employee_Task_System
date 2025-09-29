package com.example.Security.filter;

import com.example.Security.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        // 1️⃣ Get Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Extract token
        String token = authHeader.substring(7);

        // 3️⃣ Validate token
        if (!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4️⃣ Extract username and role
        String username = Jwts.parser().setSigningKey(jwtService.getSecretKey()) // optional, can use method
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        String role = jwtService.extractRole(token); // ADMIN or EMPLOYEE

        // 5️⃣ Set Authentication in Spring Security context
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + role)) // Spring requires ROLE_ prefix
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 6️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}