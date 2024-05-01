package com.hossana.expensemanager.utils;

import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;

public class LoadingIndicatorManager {
    public static void showLoading(ProgressBar loading_indicator, MaterialButton button) {
        loading_indicator.setVisibility(View.VISIBLE);
        button.setClickable(false);
        button.setText(" ");
    }

    public static void hideLoading(ProgressBar loading_indicator, MaterialButton button, String button_text) {
        loading_indicator.setVisibility(View.INVISIBLE);
        button.setText(button_text);
    }
}
