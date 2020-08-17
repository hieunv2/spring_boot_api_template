package com.artech.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncrytedPasswordUtil {
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
