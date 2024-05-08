package com.hossana.homefort.utils;

import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

public class TogglePasswordVisibility {
    static boolean isPasswordVisible = false;
    public static void toggle(EditText editText, Drawable leftDrawable, Drawable showPasswordDrawable, Drawable hidePasswordDrawable) {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            editText.setTransformationMethod(null);
            editText.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, hidePasswordDrawable, null);
        } else {
            editText.setTransformationMethod(new PasswordTransformationMethod());
            editText.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, showPasswordDrawable, null);
        }
    }
}
