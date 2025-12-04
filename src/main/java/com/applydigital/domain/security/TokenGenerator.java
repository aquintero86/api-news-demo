package com.applydigital.domain.security;

public interface TokenGenerator {
    String generate(String serviceName);
}
