package com.uca.parcialfinalncapas.exceptions;


/*
    * This class represents a custom exception for JWT authentication errors.
 */
public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
