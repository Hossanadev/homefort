package com.hossana.homefort.utils;

import android.content.Context;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.hossana.homefort.R;

public class IsValidInput
{
    public static void isValid(Context context, EditText view, Boolean isDarkMode) {
        if (isDarkMode) {
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.textinput_bg_dark));
        } else {
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.textinput_bg_light));
        }
        view.setError(null);
    }
    public static void isNotValid(Context context, EditText view, String error_msg, Boolean isDarkMode) {
        if (isDarkMode) {
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.textinput_error_bg_dark));
        } else {
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.textinput_error_bg_light));
        }
        view.setError(error_msg);
        view.requestFocus();
    }
}
