package com.interviewtracker.utility;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    // Minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter, and 1 number
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\w\\W]{8,}$";

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isStrongPassword(String password) {
        return password != null && Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }
    
    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 2;
    }
}
