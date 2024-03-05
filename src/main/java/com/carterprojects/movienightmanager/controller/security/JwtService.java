package com.carterprojects.movienightmanager.controller.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {
    String extractUsername(String token);

    Date extractExpiration(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, String> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}
