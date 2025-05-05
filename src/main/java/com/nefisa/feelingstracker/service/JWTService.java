package com.nefisa.feelingstracker.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String extractUsername(String token);
    Boolean isTokenValid(String token, UserDetails userDetails);
    String generateToken(Map<String, Object> claims, UserDetails userDetails);
}
