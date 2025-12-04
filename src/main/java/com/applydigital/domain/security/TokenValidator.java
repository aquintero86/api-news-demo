package com.applydigital.domain.security;

public interface TokenValidator {

    boolean isValid(String token);
    String extractService(String token);
}
