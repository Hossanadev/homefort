package com.hossana.homefort.utils;

import android.util.Patterns;

public class IsValidEmail {
    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
