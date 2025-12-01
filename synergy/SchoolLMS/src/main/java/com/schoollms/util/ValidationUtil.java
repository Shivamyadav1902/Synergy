package com.schoollms.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Validation Utility Class
 * Provides input validation methods
 */
public class ValidationUtil {
    
    // Email regex pattern
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    // Phone regex pattern (10 digits)
    private static final String PHONE_PATTERN = "^[0-9]{10}$";
    
    // Username pattern (alphanumeric and underscore, 3-20 chars)
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{3,20}$";
    
    /**
     * Validate email address
     * @param email Email to validate
     * @return true if email is valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Validate phone number
     * @param phone Phone number to validate
     * @return true if phone is valid
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return true; // Phone is optional
        }
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone.replaceAll("[\\s-]", ""));
        return matcher.matches();
    }
    
    /**
     * Validate username
     * @param username Username to validate
     * @return true if username is valid
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    
    /**
     * Check if string is null or empty
     * @param str String to check
     * @return true if string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Check if string is not null and not empty
     * @param str String to check
     * @return true if string has content
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * Validate marks (should be between 0 and max)
     * @param marks Marks to validate
     * @param maxMarks Maximum marks
     * @return true if marks are valid
     */
    public static boolean isValidMarks(int marks, int maxMarks) {
        return marks >= 0 && marks <= maxMarks;
    }
    
    /**
     * Sanitize string input (basic XSS prevention)
     * @param input Input string
     * @return Sanitized string
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .replaceAll("/", "&#x2F;");
    }
}
