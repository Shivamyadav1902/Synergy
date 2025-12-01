package com.schoollms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * Password Utility Class
 * Handles password hashing and validation
 */
public class PasswordUtil {
    
    /**
     * Hash password using SHA-256
     * @param password Plain text password
     * @return Hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    /**
     * Verify password against hashed password
     * @param password Plain text password
     * @param hashedPassword Hashed password from database
     * @return true if passwords match
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashOfInput = hashPassword(password);
        return hashOfInput.equals(hashedPassword);
    }
    
    /**
     * Check if password meets minimum requirements
     * @param password Password to validate
     * @return true if password is valid
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        // Add more validation rules as needed
        // E.g., must contain numbers, special characters, etc.
        return true;
    }
}
