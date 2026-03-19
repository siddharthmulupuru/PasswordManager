package com.siddharthmulupuru.passwordmanager.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        // 1. Get the Authorization header.
        String authHeader = request.getHeader("Authorization");
        
        // 2. If no header or doesn't start with "Bearer ", skip.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract token.
        String token = authHeader.substring(7);
        
        // 4. Validate token and get user ID.
        UUID userID;
        try {
            userID = jwtService.validateToken(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5. Load user from database.
        Optional<User> user = userRepository.findById(userID);

        if (user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 6. Create the "badge."
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                user.get(), // the user object
                null, // credentials (not needed, already verified)
                List.of() // authorities/roles (empty for now)
            );

        // Add request details to the badge
        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // Put the badge in the holder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
